package capstone.market.initData;

import capstone.market.domain.*;
import capstone.market.repository.*;
import capstone.market.service.ChatService;
import capstone.market.service.ImageService;
import capstone.market.service.PostService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

@Component
@Slf4j
public class DataLoader {
    @Autowired
    private PostService postService;
    @Autowired
    private MannerRepository mannerRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PurchasedRepository purchasedRepository;
    @Autowired
    private DepartMentJpaRepository departMentJpaRepository;
    //    @Autowired
//    private ChatRoomRepository chatRoomRepository;
//    @Autowired
//    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private PostDataJpaRepository postDataJpaRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private FirstTrackJpaRepository firstTrackJpaRepository;
    @Autowired
    private SecondTrackJpaRepository secondTrackJpaRepository;
    @Autowired
    ChatService chatService;
    @Autowired
    private CategoryJpaRepository categoryJpaRepository;
    @Autowired
    private FavoriteJpaRepository favoriteJpaRepository;


    /*
    @PostConstruct
    public void init() {
        Member memberA = new Member("memberA");
        Member memberB = new Member("memberB");

        memberRepository.save(memberA);
        memberRepository.save(memberB);

        Post post = new Post(memberA);
        post.setPost_title("hello");
        post.setPrice(10000);
        postRepository.savePost(post);
    }
    */


    @PostConstruct
    public void init() {
        Member minkyu = new Member("minkyu");
        minkyu.setUsername("민규");

        Member gunhee = new Member("gunhee");
        gunhee.setUsername("건희");

        Member jys = new Member("jys");
        jys.setUsername("영식");

        Member brave = new Member("brave");
        brave.setUsername("용기");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        String formattedDate = now.format(formatter);
        minkyu.setCreatedDate(formattedDate);
        brave.setCreatedDate(formattedDate);
        gunhee.setCreatedDate(formattedDate);
        jys.setCreatedDate(formattedDate);

        Image image = new Image();
        image.setImageFilename("basicprofile.png");
        imageRepository.save(image);
        CategoryType[] categories = CategoryType.values();
        DepartmentType[] departmentTypes = DepartmentType.values();
        Random random = new Random();

        Department department = new Department(DepartmentType.컴퓨터공학부);
        departMentJpaRepository.save(department);

        FirstTrack firstTrack = new FirstTrack(TrackType.웹공학트랙, department);
        firstTrackJpaRepository.save(firstTrack);

        FirstTrack firstTrack1 = new FirstTrack(TrackType.웹공학트랙, department);
        firstTrackJpaRepository.save(firstTrack1);

        SecondTrack secondTrack = new SecondTrack(TrackType.빅데이터트랙, department);
        secondTrackJpaRepository.save(secondTrack);

        SecondTrack secondTrack1 = new SecondTrack(TrackType.빅데이터트랙, department);
        secondTrackJpaRepository.save(secondTrack1);

        minkyu.setFirstTrack(firstTrack);
        minkyu.setSecondTrack(secondTrack);
        // minkyu.setImage(image1);

        gunhee.setFirstTrack(firstTrack1);
        gunhee.setSecondTrack(secondTrack1);

        jys.setFirstTrack(firstTrack1);
        jys.setSecondTrack(secondTrack1);

        brave.setFirstTrack(firstTrack1);
        brave.setSecondTrack(secondTrack1);

        jys.setFirst_college(CollegeType.상상력교양대학);
        brave.setFirst_college(CollegeType.디자인대학);

        Image image1 = new Image();
        image1.setImageFilename("aaa.png");
        imageRepository.save(image1);

        Image gunheeProfile = new Image();
        gunheeProfile.setImageFilename("gunheeProfile.png");
        imageRepository.save(gunheeProfile);

        Image braveProfile = new Image();
        braveProfile.setImageFilename("braveProfile.png");
        imageRepository.save(braveProfile);

        Image jysProfile = new Image();
        jysProfile.setImageFilename("jysProfile.png");
        imageRepository.save(jysProfile);

        jys.setImage(jysProfile);
        gunhee.setImage(gunheeProfile);
        minkyu.setImage(image1);
        brave.setImage(braveProfile);

        Manner mannerA = new Manner();
        mannerRepository.save(mannerA);
        minkyu.setManner(mannerA);

        Manner mannerB = new Manner();
        mannerRepository.save(mannerB);
        gunhee.setManner(mannerB);

        Manner mannerC = new Manner();
        mannerRepository.save(mannerC);
        jys.setManner(mannerC);

        Manner mannerD = new Manner();
        mannerRepository.save(mannerD);
        brave.setManner(mannerD);

        memberRepository.save(minkyu);
        memberRepository.save(gunhee);
        memberRepository.save(jys);
        memberRepository.save(brave);
        // user_id 가 memberA인 멤버의 트랙1: 웹공학트랙, 2트랙을 빅데이터트랙
        // 프론트에서 pk id가 4인 멤버의 트랙1, 2를 물어본다면?

        Category post1category = new Category();
        Category post2category = new Category();
        Category post3category = new Category();
        Category post4category = new Category();
        Category post5category = new Category();

        post1category.setCategory_type(CategoryType.도서);
        post2category.setCategory_type(CategoryType.생활가전);
        post3category.setCategory_type(CategoryType.전자기기);
        post4category.setCategory_type(CategoryType.전자기기);
        post5category.setCategory_type(CategoryType.뷰티미용);

        categoryJpaRepository.save(post1category);
        categoryJpaRepository.save(post2category);
        categoryJpaRepository.save(post3category);
        categoryJpaRepository.save(post4category);
        categoryJpaRepository.save(post5category);

        LocationType[] locations = LocationType.values();

//            our_memory_image1.setPost(post2);



        Post post1 = new Post();
        post1.setPost_title("제목1");
        post1.setPost_text("내용");
//        Post post1 = new Post("제목1","내용");


        Post javaBookPost = new Post();
        javaBookPost.setPost_title("객지2 교재 팝니다!");
        javaBookPost.setPost_text("메모 거의 없습니다!");

        Post macBookPost = new Post("오머~ 맥북 프로 싸게 팔아요~","진짜 찐으로 싸게 파는 거라서 가격협상은 힙들어요..");
        Post bugiPost = new Post("부기 굿즈 팔아요!","부기 팔아요 싸게 파는거에용");

        post1.setWho_posted(minkyu);
        javaBookPost.setWho_posted(minkyu);
        macBookPost.setWho_posted(gunhee);
        bugiPost.setWho_posted(brave);

//        macBookPost.generateRandomCreatedDate();

        post1.setPrice(10000);
        javaBookPost.setPrice(8000);
        macBookPost.setPrice(900000);
        bugiPost.setPrice(2000);

        // Set the created date for macBookPost with the random month, day, and current year (2023)




        //구매목록 하나 만들기
        // 멤버 a가올린 포스트를 멤버 b가 사면서 포스트에 구매 목록에 멤버가 멤버b로 바뀜
/*        Purchased purchased = new Purchased();
        purchased.setMember(gunhee);
        purchasedRepository.save(purchased);
        post1.setPurchased(purchased);*/

//        Purchased purchased2 = new Purchased();
//        purchased2.setMember(memberC);
//        purchasedRepository.save(purchased2);
//        macBookPost.setPurchased(purchased2);

        CollegeType[] collegeTypes = CollegeType.values();
        for (int i =0;i<1;i++) {
            Post dummyPost = new Post();
            int randomIndex = random.nextInt(collegeTypes.length);

            // 랜덤 CollegeType 설정
            dummyPost.setCollege(collegeTypes[randomIndex]);

            Category randomCategory = new Category();
            randomCategory.setCategory_type(categories[random.nextInt(categories.length)]);
            categoryJpaRepository.save(randomCategory);
            dummyPost.setCategory(randomCategory);

            if (dummyPost.getCategory().getCategory_type() == CategoryType.의류) {
                dummyPost.setPost_title("더미 옷 팔아요"+i);
                dummyPost.setPost_text("내용");
            } else if (dummyPost.getCategory().getCategory_type() == CategoryType.부기굿즈) {
                dummyPost.setPost_title("더미 부기굿즈 팔아요"+i);
                dummyPost.setPost_text("내용");
            } else if (dummyPost.getCategory().getCategory_type() == CategoryType.도서) {
                dummyPost.setPost_title("더미 도서 팔아요"+i);
                dummyPost.setPost_text("내용");
            } else if (dummyPost.getCategory().getCategory_type() == CategoryType.생활가전) {
                dummyPost.setPost_title("더미 생활가전 팔아요"+i);
                dummyPost.setPost_text("내용");
            } else if (dummyPost.getCategory().getCategory_type() == CategoryType.뷰티미용) {
                dummyPost.setPost_title("더미 뷰티미용 팔아요"+i);
                dummyPost.setPost_text("내용");
            } else if (dummyPost.getCategory().getCategory_type() == CategoryType.필기구) {
                dummyPost.setPost_title("더미 필기구 팔아요" + i);
                dummyPost.setPost_text("내용");
            } else if (dummyPost.getCategory().getCategory_type() == CategoryType.전자기기) {
                dummyPost.setPost_title("더미 전자기기 팔아요" + i);
                dummyPost.setPost_text("내용");
            }
            dummyPost.setWho_posted(minkyu);
            dummyPost.setPrice(10000);

            Department dummyDepartment = new Department();
            dummyDepartment.setDepartmentType(departmentTypes[random.nextInt(departmentTypes.length)]);
            departMentJpaRepository.save(dummyDepartment);
            dummyPost.setDepartment(dummyDepartment);

            dummyPost.setLocationType(locations[random.nextInt(locations.length)]);
            dummyPost.setLocation_text("101호");
            dummyPost.generateRandomCreatedDate();

            dummyPost.setItem_name("MacBook Pro 13");
//            post2.setItem_name("아이폰 14 프로 맥스 실버 256GB");
//            macBookPost.setItem_name("아이패드 에어 4세대 블루");
//            post4.setItem_name("토비의 스프링 1편");


            if (i %2 == 0) {
                Purchased dummyPurchased = new Purchased();
                dummyPurchased.setMember(gunhee);
                dummyPurchased.setPrice(dummyPost.getPrice());
                dummyPurchased.setPostTitle(dummyPost.getPost_title());
                dummyPurchased.setItem_name("얘들아 미안해 ㅠㅠ");
                dummyPurchased.setQuantity(1);
                dummyPurchased.setBuyer_username(gunhee.getUsername());
                purchasedRepository.save(dummyPurchased);
                dummyPost.setPurchased(dummyPurchased);
            }

            postRepository.savePost(dummyPost);

            if (dummyPost.getCategory().getCategory_type() == CategoryType.의류) {
                Image dummyImage0 = new Image();
                Image dummyImage1 = new Image();
                Image dummyImage2 = new Image();
                int randomNumber = random.nextInt(3) + 1;
                dummyImage0.setImageFilename("clothes" + randomNumber + "_0.jpg");
                dummyImage1.setImageFilename("clothes" + randomNumber + "_1.jpg");
                dummyImage2.setImageFilename("clothes" + randomNumber + "_2.jpg");
                dummyImage0.setPost(dummyPost);
                dummyImage1.setPost(dummyPost);
                dummyImage2.setPost(dummyPost);
                imageRepository.save(dummyImage0);
                imageRepository.save(dummyImage1);
                imageRepository.save(dummyImage2);
            } else if (dummyPost.getCategory().getCategory_type() == CategoryType.부기굿즈) {
                Image dummyImage = new Image();
                int randomNumber = random.nextInt(3) + 1;

                dummyImage.setImageFilename("boggie" + randomNumber + ".png");

                dummyImage.setPost(dummyPost);
                imageRepository.save(dummyImage);
            } else if (dummyPost.getCategory().getCategory_type() == CategoryType.전자기기) {
                Image dummyImage0 = new Image();
                Image dummyImage1 = new Image();
                Image dummyImage2 = new Image();
                int randomNumber = random.nextInt(3) + 1;
                dummyImage0.setImageFilename("elec" + randomNumber + "_0.jpeg");
                dummyImage1.setImageFilename("elec" + randomNumber + "_1.jpeg");
                dummyImage2.setImageFilename("elec" + randomNumber + "_2.jpeg");
                dummyImage0.setPost(dummyPost);
                dummyImage1.setPost(dummyPost);
                dummyImage2.setPost(dummyPost);
                imageRepository.save(dummyImage0);
                imageRepository.save(dummyImage1);
                imageRepository.save(dummyImage2);
            } else if (dummyPost.getCategory().getCategory_type() == CategoryType.뷰티미용) {

                Image dummyImage0 = new Image();
                Image dummyImage1 = new Image();
                Image dummyImage2 = new Image();
                int randomNumber = random.nextInt(3) + 1;
                dummyImage0.setImageFilename("beauty" + randomNumber + "_0.webp");
                dummyImage1.setImageFilename("beauty" + randomNumber + "_1.webp");
                dummyImage2.setImageFilename("beauty" + randomNumber + "_2.webp");
                dummyImage0.setPost(dummyPost);
                dummyImage1.setPost(dummyPost);
                dummyImage2.setPost(dummyPost);
                imageRepository.save(dummyImage0);
                imageRepository.save(dummyImage1);
                imageRepository.save(dummyImage2);
            } else if (dummyPost.getCategory().getCategory_type() == CategoryType.도서) {
                Image dummyImage = new Image();
                int randomNumber = random.nextInt(5) + 1;
                dummyImage.setImageFilename("book" + randomNumber + ".jpeg");
                dummyImage.setPost(dummyPost);
                imageRepository.save(dummyImage);
            } else if (dummyPost.getCategory().getCategory_type() == CategoryType.필기구) {
                Image dummyImage = new Image();
                dummyImage.setImageFilename("writing_implement1.png");
                dummyImage.setPost(dummyPost);
                imageRepository.save(dummyImage);
            } else if (dummyPost.getCategory().getCategory_type() == CategoryType.생활가전) {
                Image dummyImage = new Image();
                int randomNumber = random.nextInt(5) + 1;
                dummyImage.setImageFilename("appliances" + randomNumber + ".jpeg");
                dummyImage.setPost(dummyPost);
                imageRepository.save(dummyImage);
            }
        }



        post1.setCategory(post1category);
        javaBookPost.setCategory(post2category);
        macBookPost.setCategory(post3category);
        bugiPost.setCategory(post4category);

        post1.setDepartment(department);
        javaBookPost.setDepartment(department);
        macBookPost.setDepartment(department);
        bugiPost.setDepartment(department);

        post1.setLocationType(LocationType.공학관);
        post1.setLocation_text("101호");
        javaBookPost.setLocationType(LocationType.미래관);
        javaBookPost.setLocation_text("102호");
        macBookPost.setLocationType(LocationType.공학관);
        macBookPost.setLocation_text("103호");
        bugiPost.setLocationType(LocationType.풋살장);
        bugiPost.setLocation_text("104");

        post1.setItem_name("MacBook Pro 13");
        javaBookPost.setItem_name("아이폰 14 프로 맥스 실버 256GB");
        macBookPost.setItem_name("맥북 프로 14");
        bugiPost.setItem_name("토비의 스프링 1편");

        post1.setTrack(TrackType.게임그래픽디자인트랙);
        post1.setCollege(CollegeType.미래융합사회과학대학);
        Department department1 = new Department(DepartmentType.AI응용학과);
        post1.setDepartment(department1);


        javaBookPost.setTrack(TrackType.경제금융투자트랙);
        javaBookPost.setCollege(CollegeType.디자인대학);
        Department department2 = new Department(DepartmentType.문학문화콘텐츠학과);
        javaBookPost.setDepartment(department2);

        macBookPost.setTrack(TrackType.기업경제분석트랙);
        macBookPost.setCollege(CollegeType.미래플러스대학);
        Department department3 = new Department(DepartmentType.IT융합공학부);
        macBookPost.setDepartment(department3);

        bugiPost.setTrack(TrackType.글로벌비즈니스트랙);
        bugiPost.setCollege(CollegeType.IT공과대학);
        Department department4 = new Department(DepartmentType.상상력인재학부);
        bugiPost.setDepartment(department4);

        departMentJpaRepository.save(department1);
        departMentJpaRepository.save(department2);
        departMentJpaRepository.save(department3);
        departMentJpaRepository.save(department4);



        // 도서 1
        new PostBuilder().setWhoPosted(jys)
                .setTitle("토비의 스프링 세트 1,2권 팔아요")
                .setContext("토비의 스프링 1권 2권 세트로 팝니다 \n 공부하려고 삿는데 시작도 못햇네요\n" +
                        "\n" +
                        "상태는 깨끗합니다 직거래만 하려고요\n" +
                        "\n" +
                        "직거래 공학관 1층에서 가능합니다\n" +
                        "\n" +
                        "쿨거래 하시면 에누리 해드립니다")
                .setItemName("토비의스프링세트")
                .addImageFilename("1_j.jpg")
                .addImageFilename("2_j.jpg")
                .setPrice(60000)
                .setCategoryType(CategoryType.도서)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.공학관)
                .build();

        //도서2 jpa
        new PostBuilder().setWhoPosted(jys)
                .setTitle("ORM 표준 JPA 프로그래밍 팔아요")
                .setContext("사놓고 3번 아래로봤어요\n" +
                        "\n" +
                        "사진같은 파란볼펜 키워드만 밑줄친거 5-6장 내외있습니다\n" +
                        "공학관2층 휴게실에서 거래 원합니다!!\n" +
                        "쿨거래시 더 싸게 해드려요!")
                .setItemName("ORM표준JPA프로그래밍")
                .addImageFilename("3_j.jpg")
                .addImageFilename("4_j.jpg")
                .setPrice(30000)
                .setCategoryType(CategoryType.도서)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.공학관)
                .build();

        //도서3 달러구트 백화점 1, 2
        new PostBuilder().setWhoPosted(jys)
                .setTitle("[새책] 달러구트 꿈백화점 1,2 2권 / 이미예 / 팩토리나인")
                .setContext("책장에 보관만 했던 새책입니다.\n" +
                        "\n" +
                        "사진 필요시 찍어서 보내드려요.\n" +
                        "\n" +
                        "2권 정가 27,600원인데 18,000원에 가져 가세요.\n" +
                        "\n" +
                        "2권 일괄 거래 원합니다.\n" +
                        "\n" +
                        "새책이라 네고, 에눌은 어렵습니다.\n" +
                        "\n" +
                        "공학관 1층 상상파크 플러스에서 거래 원합니다!")
                .setItemName("달러구트 꿈백화점 1,2 2권")
                .addImageFilename("5_j.jpg")
                .setPrice(18000)
                .setCategoryType(CategoryType.도서)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.공학관)
                .build();

        //도서4 해리포터
        new PostBuilder().setWhoPosted(jys)
                .setTitle("해리포터(구판) 50,000원에 판매합니다.")
                .setContext("전집에서 세 권이 누락되어서 저렴하게 판매하고자 합니다.\n" +
                        "- 불의잔 2,3권\n" +
                        "\n" +
                        "- 죽음의성물 1권\n" +
                        "\n" +
                        "\u200B\n" +
                        "\n" +
                        "직거래는 공학관1층 상플에서 원합니다~.\n" +
                        "\n" +
                        "언제든 문자 주세요!")
                .setItemName("해리포터(구판)")
                .addImageFilename("6_j.jpg")
                .addImageFilename("7_j.jpg")
                .addImageFilename("8_j.jpg")
                .setPrice(18000)
                .setCategoryType(CategoryType.도서)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.공학관)
                .build();

        //도서5 전지적 독자시점
        new PostBuilder().setWhoPosted(jys)
                .setTitle("전지적독자시점 전권 1-14권 팝니다")
                .setContext("한두권 살짝 뜯어진거 빼고 상태 최상입니다."+
                        "\n" +
                        "직거래는 공학관1층 상플에서 원합니다~.\n" +
                        "\n" +
                        "언제든 문자 주세요!")
                .setItemName("전지적독자시점 전권 1-14")
                .addImageFilename("9_j.jpeg")
                .addImageFilename("10_j.jpeg")
                .setPrice(130000)
                .setCategoryType(CategoryType.도서)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.공학관)
                .build();


        //필기구1 파카 조터 스텐레스 신형 볼펜 미사용 새제품 2개 일괄 판매
        new PostBuilder().setWhoPosted(jys)
                .setTitle("파카조터 볼펜 세제품 2개 판매")
                .setContext("파카 조터 스텐레스 볼펜 신형 미사용 새제품 2개 일괄로만 판매합니다.\n" +
                        "\n" +
                        "2만원에 판매합니다. 택배비별도 4천원\n" +
                        "\n" +
                        "\u200B\n" +
                        "\n" +
                        "본드 스트리트 블랙 CT 볼펜 \n" +
                        "\n" +
                        "빅토리아 바이올렛 CT 볼펜\n" +
                        "\n" +
                        "\u200B\n" +
                        "\n" +
                        "의 2개로 박스포함 풀세트입니다.\n" +
                        "직거래는 상상관 1층에서 원합니다")
                .setItemName("파카 조터 스텐레스 볼펜 신형")
                .addImageFilename("11_j.jpg")
                .addImageFilename("12_j.jpg")
                .setPrice(20000)
                .setCategoryType(CategoryType.필기구)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.상상관)
                .build();


        //필기구2 까르띠에 볼펜
        new PostBuilder().setWhoPosted(jys)
                .setTitle("까르띠에 볼펜 판매")
                .setContext("시필만해서 상태 매우좋습니다\n" +
                        "\n" +
                        "구성품은 사진과 같습니다\n" +
                        "\n" +
                        "소모성 문자 자제해주세요\n" +
                        "\n" +
                        "상상관 1층에서 거래 원합니다! \n" +
                        "쿨거래 시 더 싸게 해드려요!")
                .setItemName("까르띠에 볼펜")
                .addImageFilename("13_j.jpg")
                .addImageFilename("14_j.jpg")
                .addImageFilename("15_j.jpg")
                .addImageFilename("16_j.jpg")
                .setPrice(400000)
                .setCategoryType(CategoryType.필기구)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.상상관)
                .build();

        //필기구3 쿠로미 3색 볼펜
        new PostBuilder().setWhoPosted(jys)
                .setTitle("쿠로미 3색 볼펜 판매")
                .setContext("짜잔!!\n" +
                        "\n" +
                        "귀여운 쿠로미 캐리어안에 소장각!!\n" +
                        "\n" +
                        "캐리어 뒤적이다보니 똑같은 볼펜이 두개 ㅡ.ㅡ\n" +
                        "\n" +
                        "쿠로미 하나씩 사들이는 재미가 있네용 ~\n" +
                        "\n" +
                        "\u200B\n" +
                        "\n" +
                        "조금 누워있다가 저녁 준비해야겠네유\n" +
                        "\n" +
                        "상상관 2층 팥고당에서 거래 원합니다!!")
                .setItemName("쿠로미 3색 볼펜")
                .addImageFilename("17_j.jpg")
                .addImageFilename("18_j.jpg")
                .setPrice(50000)
                .setCategoryType(CategoryType.필기구)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.상상관)
                .build();


        //필기구4 산리오 3색볼펜
        new PostBuilder().setWhoPosted(jys)
                .setTitle("산리오 3색볼펜 판매해요~")
                .setContext("마이멜로디 시나몬롤 한교동 포차코 쿠로미 캐릭터 있으며 3색 볼펜입니다\n" +
                        "\n" +
                        "\u200B\n" +
                        "\n" +
                        "모두 새상품입니다\n" +
                        "\n" +
                        "\u200B\n" +
                        "\n" +
                        "마이멜로디 6자루\n" +
                        "\n" +
                        "시나몬롤 8자루\n" +
                        "\n" +
                        "포차코 7자루\n" +
                        "\n" +
                        "쿠로미 5자루  \n" +
                        "\n" +
                        "남아있습니다")
                .setItemName("산리오 3색 볼펜")
                .addImageFilename("19_j.jpeg")
                .setPrice(3000)
                .setCategoryType(CategoryType.필기구)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.상상관)
                .build();



        //필기구5 디즈니스토어 프릭션 3색볼펜 (새제품)
        new PostBuilder().setWhoPosted(jys)
                .setTitle("디즈니스토어 프릭션 3색볼펜 (새제품) 판매")
                .setContext("디즈니스토어 프릭션 3색볼펜 (새제품)\n" +
                        "\n" +
                        "지워지는 3색볼펜 (0.5mm)\n" +
                        "\n" +
                        "미사용 새제품 입니다\n" +
                        "\n" +
                        "각 15,000원\n" +
                        "상상관 1층에서 거래 원합니다~")
                .setItemName("디즈니스토어 프릭션 3색 볼펜")
                .addImageFilename("20_j.jpg")
                .setPrice(15000)
                .setCategoryType(CategoryType.필기구)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.상상관)
                .build();



        //생활가전1
        new PostBuilder().setWhoPosted(jys)
                .setTitle("컴퓨터 책상 저렴히 판매합니다")
                .setContext("컴퓨터 책상 저렴히 판매합니다\n" +
                        "까사미아에서 약 50만원에 구매했습니다\n" +
                        "가로 세로 높이 140 60 74로 2인이 사무작업하기 충분한 사이즈 입니다!\n" +
                        "상태 너무 좋고 아주 잘 사용하고 있으나 2개월 후 프랑스 이민 계획이 있어 슬슬 정리합니다.\n" +
                        "10만원에 판매합니다")
                .setItemName("까사미아 컴퓨터 책상")
                .addImageFilename("21_j.jpg")
                .setPrice(100000)
                .setCategoryType(CategoryType.생활가전)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.미래관)
                .build();

        //생활가전2
        new PostBuilder().setWhoPosted(jys)
                .setTitle("필립스 HD9743/45 에어프라이어 판매")
                .setContext("필립스 HD9743/45 에어프라이어 저렴히 판매합니다!\n" +
                        "결혼하면서 구매하고 1년 정도 사용했는데 프랑스 이민 준비중이라 저렴히 판매합니다\n" +
                        "거의 새것 같은 상태이고 비닐도 채 안뜯은 부분들도 있습니다.\n" +
                        "구매 당시 할인 받아 25만원에 코스트코에서 구매했던 모델입니다\n" +
                        "8만원에 판매합니다\n" +
                        "미래관 지하 1층에서 거래 원합니다!!")
                .setItemName("필립스 에어프라이어")
                .addImageFilename("22_j.jpg")
                .addImageFilename("23_j.jpg")
                .setPrice(80000)
                .setCategoryType(CategoryType.생활가전)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.미래관)
                .build();


        //생활가전3
        new PostBuilder().setWhoPosted(jys)
                .setTitle("수아비 방역소독기 팔아요")
                .setContext("방역 소독기 팝니다.\n" +
                        "시중가는 387000이고요\n" +
                        "새거 15만에 내놓습니다\n" +
                        "직거래는 미래관 지하1 층에서 가능합니다\n")
                .setItemName("수아비 방역소독기")
                .addImageFilename("24_j.jpg")
                .addImageFilename("25_j.jpg")
                .setPrice(150000)
                .setCategoryType(CategoryType.생활가전)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.미래관)
                .build();



        //생활가전4
        new PostBuilder().setWhoPosted(jys)
                .setTitle("삼성 UHD 50인치 TV 판매")
                .setContext("삼성 UHD 50인치 TV 저렴히 판매합니다\n" +
                        "프랑스 이민 준비중이라 저렴히 판매합니다\n" +
                        "패널교체 한번 했어서 흠집이나 불량화소 없이 새것 같은 상태이고 깨끗하게 사용했습니다.\n" +
                        "해외 직구 등 A/S 불가한 상품 아닙니다 정식 서비스 전부 가능합니다\n" +
                        "구매가 약 150 가량이나 40만원에 판매하겠습니다")
                .setItemName("삼성 UHD 50인치 TV")
                .addImageFilename("26_j.jpg")
                .addImageFilename("27_j.jpg")
                .setPrice(400000)
                .setCategoryType(CategoryType.생활가전)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.미래관)
                .build();


        //생활가전5
        new PostBuilder().setWhoPosted(jys)
                .setTitle("삼성 전자레인지 팔아요")
                .setContext("삼성 전자레인지 3만원\n" +
                        "저의 자취 생활과 함께한 전자레인지 입니다. \n" +
                        "외식 고물가 시대에 집에서 밥해먹을려면 필수 입니다. \n" +
                        "가전은 본 기능에만 충실하면 된다 생각합니다.\n" +
                        "잘 작동됩니다.\n" +
                        "미래관 지하 1층에서 거래 원합니다")
                .setItemName("삼성 전자레인지")
                .addImageFilename("28_j.jpg")
                .setPrice(30000)
                .setCategoryType(CategoryType.생활가전)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.미래관)
                .build();


        //의류 1
        new PostBuilder().setWhoPosted(jys)
                .setTitle("커스텀멜로우 반팔 셔츠 판매합니다")
                .setContext("깔끔한 색상에 스티치 라인으로 포인트를 준 셔츠 입니다 \n" +
                        "오픈 카라 디자인으로 캐쥬얼하게 연출이 가능하고\n" +
                        "깔끔하게 입으실때도 좋습니다!\n" +
                        "코튼 나일론 혼방소재로 매우 가볍고 여름에 시원하게 입으실 수 있습니다\n" +
                        "구매 후 실착용 3회 미만으로 상태 매우 좋습니다\n" +
                        "뒷면 아주 작은 한땀?정도 살짝 뜯김 있지만 아주 미세하고\n" +
                        "작은 부위라 신경 쓰이는 정도는 아닙니다! \n" +
                        "표기상 사이즈 95 입니다\n" +
                        "(마른 100분들까지 입으실 수 있습니다)\n" +
                        "색상은 어두운 네이비 입니다\n" +
                        "직거래 인성관 1층에서 합니다! ")
                .setItemName("커스텀멜로우 반팔 셔츠")
                .addImageFilename("29_j.jpg")
                .addImageFilename("30_j.jpg")
                .addImageFilename("31_j.jpg")
                .addImageFilename("32_j.jpg")
                .setPrice(50000)
                .setCategoryType(CategoryType.의류)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.인성관)
                .build();

        //의류 2
        new PostBuilder().setWhoPosted(jys)
                .setTitle("하와이안 셔츠 반팔 판매합니다")
                .setContext("NII 하와이안 셔츠 판매합니다\n" +
                        "49,900원 택 제거 안 한 상태로 보관중입니다\n" +
                        "표기상 사이즈 L 100 입니다\n" +
                        "국내 남성 100- 마른 105까지 추천드립니다\n" +
                        "직거래 인성관 1층에서 진행해요!")
                .setItemName("하와이안 셔츠 반팔")
                .addImageFilename("33_j.jpg")
                .addImageFilename("34_j.jpg")
                .addImageFilename("35_j.jpg")
                .setPrice(20000)
                .setCategoryType(CategoryType.의류)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.인성관)
                .build();

        //의류 3나이키 쪼리 슬리퍼 판매합니다
        new PostBuilder().setWhoPosted(jys)
                .setTitle("나이키 쪼리 슬리퍼 판매합니다")
                .setContext("미국 여행할 때 사왔던건데 안 신고 보관만 하고 있었던거라\n" +
                        "\n" +
                        "택만 제거 된 새상품 입니다\n" +
                        "쿠션감 있는 쪼리라 인기 많았던 제품이에요\n" +
                        "박스는 따로 없습니다\n" +
                        "표기상 사이즈 240 입니다\n" +
                        "직거래 인성관 1층에서 진행해요")
                .setItemName("나이키 쪼리 슬리퍼")
                .addImageFilename("36_j.jpg")
                .addImageFilename("37_j.jpg")
                .addImageFilename("38_j.jpg")
                .setPrice(30000)
                .setCategoryType(CategoryType.의류)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.인성관)
                .build();


        //의류 4나이키 쪼리 슬리퍼 판매합니다
        new PostBuilder().setWhoPosted(jys)
                .setTitle("새상품) 밀로 홀리데이 시그니처 볼캡")
                .setContext("밀로 홀리데이 시그니처 볼캡 판매합니다\n" +
                        "한개는 택 그대로 있고\n" +
                        "나머지 한개는 택 제거만 된 새상품입니다\n" +
                        "모자가 어울리는 얼굴형이 아니라서 판매해요\n" +
                        "직거래 수원\n" +
                        "택배비 4000원 별도\n" +
                        "색상 파인그린, 차콜\n" +
                        "각 25000원 입니다\n" +
                        "직거래는 인성관 1층에서 진행합니다")
                .setItemName("밀로 홀리데이 시그니처 볼캡")
                .addImageFilename("39_j.jpg")
                .addImageFilename("40_j.jpg")
                .addImageFilename("41_j.jpg")
                .setPrice(25000)
                .setCategoryType(CategoryType.의류)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.인성관)
                .build();

        //의류 5 나이키 쪼리 슬리퍼 판매합니다
        new PostBuilder().setWhoPosted(jys)
                .setTitle("20FW 이스트로그 셔츠 (XL) 판매")
                .setContext("268000원에 구매하여 실착용 5회 미만으로 전체적으로 좋은 상태 유지중입니다.\n" +
                        "표기상 사이즈 XL 이며 사이트에서 XL 는 품절입니다!\n" +
                        "그만큼 많이들 찾으시는 사이즈 입니다\n" +
                        "셔츠와 가디건 느낌을 동시에 느낄 수 있는 제품으로\n" +
                        "허리 측면 스트링은 미국 M69 방탄조끼의 디테일을 모티브로하여\n" +
                        "매력있는 디자인입니다\n" +
                        "인성관 1층에서 직거래 원합니다 ")
                .setItemName("이스트로그 셔츠")
                .addImageFilename("42_j.jpg")
                .addImageFilename("43_j.jpg")
                .addImageFilename("44_j.jpg")
                .setPrice(268000)
                .setCategoryType(CategoryType.의류)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.인성관)
                .build();


        //전자기기 1
        new PostBuilder().setWhoPosted(jys)
                .setTitle("아이폰12미니 레드색상 256기가 판매")
                .setContext("아이폰12미니 레드색상 256기가 상태좋은단말기 39만에 판매합니다\n" +

                        "선택약정할인25% 확정기변 가능 합니\n" +

                        "3사 모든통신사,알뜰폰 사용 가능 합니다\n" +

                        "배터리 효율 80% 이며 사설수리 이력 없습니다\n" +

                        "액정화면 파손 기스 잔상 빛샘 불량화소없는 단말기 입니다\n" +
                        "상상관 1층에서 거래 진행합니다!!")
                .setItemName("아이폰 12 미니 레드 256G")
                .addImageFilename("45_j.jpg")
                .addImageFilename("46_j.jpg")
                .addImageFilename("47_j.jpg")
                .setPrice(390000)
                .setCategoryType(CategoryType.전자기기)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.상상관)
                .build();



        //전자기기 2
        new PostBuilder().setWhoPosted(jys)
                .setTitle("아이폰 11프로 256G 블랙 판매")
                .setContext("아이폰 11프로 256G 블랙 액정무잔상 잔상 파손없는 최저가 38만원팝니다\n" +
                        "선택약정할인25% , 확정기변 가능합니다\n" +
                        "3사 모든통신사, 알뜰폰 사용 가능 합니다\n" +
                        "배터리효율 75% 사설수리 침수이력없습니다" +
                        "상상관 1층에서 거래 진행합니다!!")
                .setItemName("아이폰 11프로 256G 블랙")
                .addImageFilename("48_j.jpg")
                .addImageFilename("49_j.jpg")
                .addImageFilename("50_j.jpg")
                .setPrice(390000)
                .setCategoryType(CategoryType.전자기기)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.상상관)
                .build();

        //전자기기 3
        new PostBuilder().setWhoPosted(jys)
                .setTitle("갤럭시 S10E 화이트 128G 팝니다")
                .setContext("전체적인 외관 A급 입니다\n" +
                        "잔상 없으며 모든 기능 정상작동 합니다\n" +
                        "유심끼워 사용 가능 합니다\n" +
                        "최초 통신사 LG이나 모든 통신사 사용 가능 합니다\n" +
                        "최초 통화일 2019년 11월 23일 입니다\n" +
                        "상상관 1층에서 직거래 원합니다!")
                .setItemName("갤럭시 S10E 화이트 128G")
                .addImageFilename("51_j.jpg")
                .addImageFilename("52_j.jpg")
                .addImageFilename("53_j.jpg")
                .setPrice(390000)
                .setCategoryType(CategoryType.전자기기)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.상상관)
                .build();



        //전자기기 4
        new PostBuilder().setWhoPosted(jys)
                .setTitle("아이패드9세대 64기가 스페이스그레이 팝니다")
                .setContext("아이패드 9세대 스페이스그레이 64기가 와이파이\n" +
                        "올해 1월 신품 구매하여 집에서만 사용했구요\n" +
                        "사용빈도가 적어 판매합니다\n" +
                        "구성품은 충전기 본체 박스 케이스 입니다\n" +
                        "직거래는 상상관 1층에서 원합니다!")
                .setItemName("아이패드9세대 64기가 스페이스그레이")
                .addImageFilename("54_j.jpg")
                .setPrice(250000)
                .setCategoryType(CategoryType.전자기기)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.상상관)
                .build();


        //전자기기 5
        new PostBuilder().setWhoPosted(jys)
                .setTitle("아이패드 8세대 32gb 택포 23에 팝니다")
                .setContext("2년전에 사서 거의 인강만 들어서 깨끗합니다\n" +
                        "필름 제가 붙여서 공기방울 들어간것이니 아무이상도 없고 찍힌자국 없어요\n" +
                        "케이스에만 넣어두어서 먼지가 많아요\n" +
                        "짭펜슬이랑 충전기랑 본체는 있는데 상자는 아무리 찾아도 없습니다ㅠ\n" +
                        "직거래는 상상관 1층에서 원합니다!")
                .setItemName("아이패드 8세대 32gb")
                .addImageFilename("55_j.jpg")
                .addImageFilename("56_j.jpg")
                .setPrice(230000)
                .setCategoryType(CategoryType.전자기기)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.상상관)
                .build();

        //뷰티미용 1
        new PostBuilder().setWhoPosted(jys)
                .setTitle("에스까다 이스페셜리 오드퍼퓸 향수 50ml 팝니다")
                .setContext("미개봉) 에스까다 이스페셜리 오드퍼퓸 50ml 향수 팝니다\n" +
                        "향수 상태는 미개봉 입니다\n" +
                        "직거래는 공학관 1층에서 원합니다!")
                .setItemName("에스까다 이스페셜리 오드퍼퓸 향수 50ml")
                .addImageFilename("57_j.JPG")
                .addImageFilename("58_j.JPG")
                .addImageFilename("59_j.JPG")
                .setPrice(50000)
                .setCategoryType(CategoryType.뷰티미용)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.공학관)
                .build();


        //뷰티미용 2
        new PostBuilder().setWhoPosted(jys)
                .setTitle("에스까다 이스페셜리 오드퍼퓸 향수 50ml 팝니다")
                .setContext("미개봉) 에스까다 이스페셜리 오드퍼퓸 50ml 향수 팝니다\n" +
                        "향수 상태는 미개봉 입니다\n" +
                        "직거래는 공학관 1층에서 원합니다!")
                .setItemName("에스까다 이스페셜리 오드퍼퓸 향수 50ml")
                .addImageFilename("57_j.JPG")
                .addImageFilename("58_j.JPG")
                .addImageFilename("59_j.JPG")
                .setPrice(50000)
                .setCategoryType(CategoryType.뷰티미용)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.공학관)
                .build();

        //뷰티미용 3
        new PostBuilder().setWhoPosted(jys)
                .setTitle("(새제품)톰포드 향수 패뷸러스 50ML")
                .setContext("톰포드 패뷸러스 50ML 2025.10.27\n" +
                        "25만원\n" +
                        "직거래나 구매 결정시 구매내역 확인,동봉해 드립니다\n" +
                        "제품 구매 결정하시기 전에 충분히 구매 제품에 대한 가격비교 검색하시고\n" +
                        "판매 상품이 맘에 드시면 연락 주시기 바랍니다.\n" +
                        "직거래는 공학관 1층에서 원합니다!")
                .setItemName("톰포드 향수 패뷸러스 50ML")
                .addImageFilename("60_j.jpg")
                .setPrice(250000)
                .setCategoryType(CategoryType.뷰티미용)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.공학관)
                .build();

        //뷰티미용 4
        new PostBuilder().setWhoPosted(jys)
                .setTitle("베르사체 에로스 맨 EDT 100ML 팝니다")
                .setContext("남성향수 1위 할정로도 베스트아이템으로 꼽히는 명품 브랜드 베르사체 에로스 향수 제품이에요.\n" +
                        "제가 제일 좋아하는 향으로 완전 강추!! \n" +
                        "유행타지않고 4계절 꾸준히 인기있는 남자향수 입니다.\n" +
                        "중후하면서 멋스러운 기분좋은 향으로 베이스 노트가 끝장인 최고의 명품 향수입니다. \n" +
                        "베르사체 특유의 고급스러운 디자인으로 제작된 향수병으로 선물용으로도 적극 추천해드립니다. \n" +
                        "직거래는 공학관 1층 상상플러스에서 진행할게요!")
                .setItemName("베르사체 에로스 맨 EDT 100ML ")
                .addImageFilename("61_j.jpg")
                .addImageFilename("62_j.jpg")
                .setPrice(45000)
                .setCategoryType(CategoryType.뷰티미용)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.공학관)
                .build();

        //뷰티미용 5
        new PostBuilder().setWhoPosted(jys)
                .setTitle("휴고보스 소울 90ml")
                .setContext("휴고보스 소울 edt 90ml\n" +
                        "조금 사용했습니다.\n" +
                        "향수양은 사진을 참고하세요.\n" +
                        "뚜껑은 없습니다.\n" +
                        "박스도 없어요.\n" +
                        "46,000원\n" +
                        "직거래는 공학관 1층에서 원합니다!")
                .setItemName("휴고보스 소울 90ml")
                .addImageFilename("63_j.jpg")
                .addImageFilename("64_j.jpg")
                .setPrice(46000)
                .setCategoryType(CategoryType.뷰티미용)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.공학관)
                .build();



        //  부기굿즈 1
        new PostBuilder().setWhoPosted(jys)
                .setTitle("부기 우산 팝니다")
                .setContext("부기 우산 팝니다\n" +
                        "조금 사용했습니다.\n" +
                        "너무 귀여운 부기 우산 가져가세요~~.\n" +
                        "46,000원\n" +
                        "직거래는 공학관 1층에서 원합니다!")
                .setItemName("부기우산")
                .addImageFilename("65_j.jpeg")
                .setPrice(30000)
                .setCategoryType(CategoryType.부기굿즈)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.공학관)
                .build();

        //  부기굿즈 2
        new PostBuilder().setWhoPosted(jys)
                .setTitle("부기 토트백 팝니다")
                .setContext("부기 토트백 팝니다\n" +
                        "조금 사용했습니다.\n" +
                        "너무 귀여운 부기 토트백 가져가세요~~.\n" +
                        "30,000원\n" +
                        "직거래는 공학관 1층에서 원합니다!")
                .setItemName("부기 토트백")
                .addImageFilename("66_j.png")
                .setPrice(30000)
                .setCategoryType(CategoryType.부기굿즈)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.공학관)
                .build();

        //  부기굿즈 3
        new PostBuilder().setWhoPosted(jys)
                .setTitle("부기 토트백 흰,검 팝니다")
                .setContext("부기 토트백 팝니다\n" +
                        "조금 사용했습니다.\n" +
                        "너무 귀여운 부기 토트백 흰,검 각각 1개 가져가세요~~.\n" +
                        "30,000원\n" +
                        "직거래는 공학관 1층에서 원합니다!")
                .setItemName("부기 토트백")
                .addImageFilename("67_j.jpeg")
                .setPrice(30000)
                .setCategoryType(CategoryType.부기굿즈)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.공학관)
                .build();

        //  부기굿즈 4
        new PostBuilder().setWhoPosted(jys)
                .setTitle("부기 인형 팝니다")
                .setContext("부기 인형 팝니다\n" +
                        "조금 사용했습니다.\n" +
                        "너무 귀여운 부기 인형 가져가세요~~.\n" +
                        "46,000원\n" +
                        "직거래는 공학관 1층에서 원합니다!")
                .setItemName("부기인형")
                .addImageFilename("68_j.jpeg")
                .setPrice(15000)
                .setCategoryType(CategoryType.부기굿즈)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.공학관)
                .build();

        //  부기굿즈 5
        new PostBuilder().setWhoPosted(jys)
                .setTitle("부기 공책 팝니다")
                .setContext("부기 공책 팝니다\n" +
                        "조금 사용했습니다.\n" +
                        "너무 귀여운 부기 공책 가져가세요~~.\n" +
                        "10000원\n" +
                        "직거래는 공학관 1층에서 원합니다!")
                .setItemName("부기공책")
                .addImageFilename("69_j.jpeg")
                .setPrice(10000)
                .setCategoryType(CategoryType.부기굿즈)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.공학관)
                .build();
























    }

    private void setPostImageHard(String imageFilename, Post post) {
        Image imageHard = new Image();
        imageHard.setImageFilename(imageFilename);
        imageHard.setPost(post);
        imageRepository.save(imageHard);
    }

    private void makeHardCodePost(Member whoPosted, String title, String context, String itemName, String imageFilename,
                                  int price, CategoryType categoryType,
                                  DepartmentType departmentType, LocationType locationType) {
        Post hardcodePost = new Post();
        hardcodePost.setPost_title(title);
        hardcodePost.setPost_text(context);
        hardcodePost.setWho_posted(whoPosted);
        hardcodePost.setItem_name(itemName);
        hardcodePost.setPrice(price);

        Category category = new Category();
        category.setCategory_type(categoryType);
        categoryJpaRepository.save(category);
        hardcodePost.setCategory(category);

        Department department = new Department(departmentType);
        departMentJpaRepository.save(department);
        hardcodePost.setDepartment(department);
        hardcodePost.setLocationType(locationType);

        postRepository.savePost(hardcodePost);

        Image imageHard = new Image();
        imageHard.setImageFilename(imageFilename);
        imageHard.setPost(hardcodePost);
        imageRepository.save(imageHard);
    }



    @Data
    static class ChatRoomResponse {
        private String sender;
        private String receiver;

        public ChatRoomResponse(String memberA, String memberB) {
            this.sender = memberA;
            this.receiver = memberB;
        }
    }

    @Data
    static class ChaRoomResponse {
        private String usernameA;
        private String usernameB;
        private String message;

        public ChaRoomResponse(Post post, ChatMessage chatMessage) {
            this.usernameA = post.getWho_posted().getUsername();
            this.usernameB = chatMessage.getMember().getUsername();
            this.message = chatMessage.getMessage();
        }
    }
    public class PostBuilder {
        private Member whoPosted;
        private String title;
        private String context;
        private String itemName;
        private ArrayList<String> imageFilenames = new ArrayList<>();
        private int price;
        private CategoryType categoryType;
        private DepartmentType departmentType;
        private LocationType locationType;

        public PostBuilder setWhoPosted(Member whoPosted) {
            this.whoPosted = whoPosted;
            return this;
        }

        public PostBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public PostBuilder setContext(String context) {
            this.context = context;
            return this;
        }

        public PostBuilder setItemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        public PostBuilder addImageFilename(String imageFilename) {
            this.imageFilenames.add(imageFilename);
            return this;
        }

        public PostBuilder setPrice(int price) {
            this.price = price;
            return this;
        }

        public PostBuilder setCategoryType(CategoryType categoryType) {
            this.categoryType = categoryType;
            return this;
        }

        public PostBuilder setDepartmentType(DepartmentType departmentType) {
            this.departmentType = departmentType;
            return this;
        }

        public PostBuilder setLocationType(LocationType locationType) {
            this.locationType = locationType;
            return this;
        }

        public void build() {
            Post hardcodePost = new Post();
            hardcodePost.setPost_title(title);
            hardcodePost.setPost_text(context);
            hardcodePost.setWho_posted(whoPosted);
            hardcodePost.setItem_name(itemName);
            hardcodePost.setPrice(price);

            Category category = new Category();
            category.setCategory_type(categoryType);
            categoryJpaRepository.save(category);
            hardcodePost.setCategory(category);

            Department department = new Department(departmentType);
            departMentJpaRepository.save(department);
            hardcodePost.setDepartment(department);
            hardcodePost.setLocationType(locationType);

            postRepository.savePost(hardcodePost);

            for(String imageFilename : imageFilenames) {
                Image imageHard = new Image();
                imageHard.setImageFilename(imageFilename);
                imageHard.setPost(hardcodePost);
                imageRepository.save(imageHard);
            }
        }
    }
}

