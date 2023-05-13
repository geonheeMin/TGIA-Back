package capstone.market.service;

import capstone.market.domain.Member;
import capstone.market.domain.Post;
import capstone.market.domain.Purchased;
import capstone.market.kakao_dto.KakaoApproveResponse;
import capstone.market.kakao_dto.KakaoCancelResponse;
import capstone.market.kakao_dto.KakaoPayDto;
import capstone.market.kakao_dto.KakaoReadyResponse;
import capstone.market.profile_dto.PostSellDetailDto;
import capstone.market.repository.MemberRepository;
import capstone.market.repository.PostDataJpaRepository;
import capstone.market.repository.PostRepository;
import capstone.market.repository.PurchasedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoPayService {

    private final PurchasedRepository purchasedRepository;

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    static final String cid = "TC0ONETIME"; // 가맹점 테스트 코드
    static final String admin_Key = "6be870a80ca53d5aabf353bed986653b"; // 공개 조심! 본인 애플리케이션의 어드민 키를 넣어주세요
    private KakaoReadyResponse kakaoReady;
    /**
     * 상품 이름, 상품 가격 , 구매자 ,판매자, 가맹점 주문 번호 = 부기마켓 = 886916,
     */
    private String item_name;
    private Integer item_price;

    private Long seller_id; //판매자 유저 기본키
    private Long buyer_id;  // 구매 유저 기본키
    private Integer partner_order_id = 886916;
    private Long partner_user_id;  // 판매자 아이디

    private Long post_id; //판매 게시글의 기본키


    public KakaoReadyResponse kakaoPayReady(KakaoPayDto kakaoPayDto) {

        /**
         * 아이템 이름 넣고, 구매자아이디 넣어주고 , 가격 넣어주자 , 디벨로퍼에 나온 기준 엄격히 지키자 가격
         */
        item_name = kakaoPayDto.getItem_name(); //상품 이름
        item_price = kakaoPayDto.getPrice();    //상품 가격
        seller_id = kakaoPayDto.getUser_id();  //판매자
        buyer_id = kakaoPayDto.getBuyer_id(); //구매자
        post_id = kakaoPayDto.getPost_id(); //게시글 기본 키

//        Long buyerId = kakaoPayDto.getBuyer_id();
//        String itemName = kakaoPayDto.getItem_name();


        // 카카오페이 요청 양식
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("partner_order_id", String.valueOf(partner_order_id));
        parameters.add("partner_user_id", String.valueOf(seller_id));
        parameters.add("item_name", item_name);
        parameters.add("quantity", String.valueOf(1)); // 상품수량
        parameters.add("total_amount", String.valueOf(item_price)); //상품 총액
        //parameters.add("vat_amount", String.valueOf(item_price*0.1)); //상품총액 - 상품 비과세 금액 에 10퍼센트 => 값 안보내면 자동저장
        parameters.add("tax_free_amount", String.valueOf(0));//상품 비과세 금액
        //ameters.add("greenDeposit", String.valueOf(1000)); 필수 요건 아님
    
        parameters.add("approval_url", "http://223.194.132.170:8080/payment/success"); // 성공 시 redirect url
        parameters.add("cancel_url", "http://223.194.132.170:8080/payment/cancel"); // 취소 시 redirect url
        parameters.add("fail_url", "http://223.194.132.170:8080/payment/fail"); // 실패 시 redirect url


        //http://43.201.77.124:8080 => ec2주소
        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/ready";
        kakaoReady = restTemplate.postForObject(
                url,
                requestEntity,
                KakaoReadyResponse.class);

        return kakaoReady;
    }

    /**
     * 결제 완료 승인
     */
    public KakaoApproveResponse ApproveResponse(String pgToken) {


        /**
         * 구매내역 만들어주자(buyer도 설정해주자.)
         */



        Purchased purchased = new Purchased();
        purchased.setMember(memberRepository.findOne(buyer_id));
        purchased.setPrice(item_price);
        purchased.setProductName(item_name);
        purchasedRepository.save(purchased);
        Post post = postRepository.findOne(post_id);
        post.setPurchased(purchased);
        postRepository.savePost(post);


        /**
         * 이부분 값 설정 잘해줘야한다 오류나면 디벨로퍼 보고 제한사항 다 깔끔하게 적자.
         */
        // 카카오 요청
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", kakaoReady.getTid());
        parameters.add("partner_order_id", String.valueOf(partner_order_id));
        parameters.add("partner_user_id", String.valueOf(seller_id));
        parameters.add("pg_token", pgToken);


        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        KakaoApproveResponse approveResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/approve",
                requestEntity,
                KakaoApproveResponse.class);


        return approveResponse;
    }

    /**
     * 결제 환불
     */
    public KakaoCancelResponse kakaoCancel() {

        // 카카오페이 요청
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", "환불할 결제 고유 번호");
        parameters.add("cancel_amount", "환불 금액");
        parameters.add("cancel_tax_free_amount", "환불 비과세 금액");
        parameters.add("cancel_vat_amount", "환불 부가세");

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        KakaoCancelResponse cancelResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/cancel",
                requestEntity,
                KakaoCancelResponse.class);

        return cancelResponse;
    }

    /**
     * 카카오 요구 헤더값
     */
    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "KakaoAK " + admin_Key;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpHeaders;
    }
}