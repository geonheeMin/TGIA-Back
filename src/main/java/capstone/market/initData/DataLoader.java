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
import java.util.concurrent.ThreadLocalRandom;

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

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("영상편집 책 팝니다")
                .setContext("국방부 진중문고까지 등재된 책입니다.")
                .setItemName("프리미어 프로 교재")
                .addImageFilename("geonhee_book1_1.jpeg")
                .addImageFilename("geonhee_book1_2.jpeg")
                .addImageFilename("geonhee_book1_3.jpeg")
                .setPrice(15000)
                .setCategoryType(CategoryType.도서)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.상상관)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("데이터마이닝 교재")
                .setContext("제 인생 최고의 강의입니다. 꼭 들으세요.")
                .setItemName("데이터마이닝 교재")
                .addImageFilename("geonhee_book2_1.jpeg")
                .addImageFilename("geonhee_book2_2.jpeg")
                .addImageFilename("geonhee_book2_3.jpeg")
                .setPrice(15000)
                .setCategoryType(CategoryType.도서)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.공학관)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("C++ 교재 팔아요")
                .setContext("교재 싸게 팔아요")
                .setItemName("프리미어 프로 교재")
                .addImageFilename("geonhee_book3_1.jpeg")
                .addImageFilename("geonhee_book3_2.jpeg")
                .addImageFilename("geonhee_book3_3.jpeg")
                .setPrice(10000)
                .setCategoryType(CategoryType.도서)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.미래관)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("히가시노 게이고 호숫가 살인사건")
                .setContext("진짜 재밌습니다. 꼭 읽으세요.")
                .setItemName("호숫가 살인사건")
                .addImageFilename("geonhee_book4_1.jpeg")
                .addImageFilename("geonhee_book4_2.jpeg")
                .setPrice(27000)
                .setCategoryType(CategoryType.도서)
                .setDepartmentType(DepartmentType.크리에이티브인문학부)
                .setLocationType(LocationType.상상빌리지)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("쓸모없는 지식")
                .setContext("쓸모없는 지식의 쓸모")
                .setItemName("쓸모없는 지식의 쓸모")
                .addImageFilename("geonhee_book5_1.jpeg")
                .setPrice(8000)
                .setCategoryType(CategoryType.도서)
                .setDepartmentType(DepartmentType.크리에이티브인문학부)
                .setLocationType(LocationType.상상관)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("아이폰 6s 로즈골드 팔아요")
                .setContext("상태 A급, 128GB, 로즈골드 팝니다. 배터리 97% 입니다.")
                .setItemName("iPhone 6s")
                .addImageFilename("geonhee_digital1_1.jpeg")
                .addImageFilename("geonhee_digital1_2.jpeg")
                .setPrice(100000)
                .setCategoryType(CategoryType.전자기기)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.상관없음)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("닌텐도 스위치 팝니다.")
                .setContext("정신차리고 공부하려고 합니다. 동숲 에디션입니다.")
                .setItemName("닌텐도 스위치 동숲 에디션")
                .addImageFilename("geonhee_digital2_1.jpeg")
                .addImageFilename("geonhee_digital2_2.jpeg")
                .addImageFilename("geonhee_digital2_3.jpeg")
                .setPrice(250000)
                .setCategoryType(CategoryType.전자기기)
                .setDepartmentType(DepartmentType.크리에이티브인문학부)
                .setLocationType(LocationType.풋살장)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("기계식 키보드 팔아요")
                .setContext("더 좋은 거 선물 받아서 싸게 팔아요. 청축이고 예쁩니다.")
                .setItemName("기계식 키보드")
                .addImageFilename("geonhee_digital3_1.jpeg")
                .addImageFilename("geonhee_digital3_2.jpeg")
                .setPrice(20000)
                .setCategoryType(CategoryType.전자기기)
                .setDepartmentType(DepartmentType.크리에이티브인문학부)
                .setLocationType(LocationType.낙산관)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("버즈 플러스 팔게요")
                .setContext("버즈 플러스 화이트 본체만 팔아요.")
                .setItemName("버즈 플러스")
                .addImageFilename("geonhee_digital4_1.jepg")
                .addImageFilename("geonhee_digital4_2.jpeg")
                .addImageFilename("geonhee_digital4_3.jpeg")
                .setPrice(50000)
                .setCategoryType(CategoryType.전자기기)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.상상빌리지)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("게이밍 노트북 팝니다")
                .setContext("레노버 게이밍 노트북 팔아요. 얼마 안 써서 깨끗해요.")
                .setItemName("레노버 게이밍 노트북")
                .addImageFilename("geonhee_digital5_1.jpeg")
                .addImageFilename("geonhee_digital5_2.jpeg")
                .addImageFilename("geonhee_digital5_3.jpeg")
                .setPrice(750000)
                .setCategoryType(CategoryType.전자기기)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.공학관)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("샤오미 로봇청소기 팝니다.")
                .setContext("샤오미 로봇청소기 팔아요. 말 잘 듣고 청소 열심히 하는 강아지같은 친구입니다.")
                .setItemName("샤오미 로봇청소기")
                .addImageFilename("geonhee_life1_1.jpeg")
                .addImageFilename("geonhee_life1_2.jpeg")
                .setPrice(130000)
                .setCategoryType(CategoryType.생활가전)
                .setDepartmentType(DepartmentType.사회과학부)
                .setLocationType(LocationType.상상관)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("전자레인지 20L 팝니다.")
	.setContext("이사가야돼서 전자레인지 팝니다. 계란 한 번 돌려서 터진 거 말곤 문제 없습니다.")
                .setItemName("전자레인지 20L")
	.addImageFilename("geonhee_life2_1.jpeg")
                .addImageFilename("geonhee_life2_2.jpeg")
                .setPrice(80000)
                .setCategoryType(CategoryType.생활가전)
                .setDepartmentType(DepartmentType.크리에이티브인문학부)
                .setLocationType(LocationType.미래관)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("선풍기 팔아요.")
                .setContext("동그란 선풍기 팝니다. 청소해서 드릴 거니까 바로 쓰면 됩니다.")
                .setItemName("선풍기")
                .addImageFilename("geonhee_life3_1.jpeg")
                .addImageFilename("geonhee_life3_2.jpeg")
                .setPrice(65000)
                .setCategoryType(CategoryType.생활가전)
                .setDepartmentType(DepartmentType.IT융합공학부)
                .setLocationType(LocationType.공학관)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("브라운 s9 전기면도기")
                .setContext("네고 안 합니다.")
                .setItemName("브라운 s9 전기면도기")
                .addImageFilename("geonhee_life4_1.jpeg")
                .addImageFilename("geonhee_life4_2.jpeg")
                .addImageFilename("geonhee_life4_3.jpeg")
                .setPrice(17000)
                .setCategoryType(CategoryType.생활가전)
                .setDepartmentType(DepartmentType.크리에이티브인문학부)
                .setLocationType(LocationType.상상빌리지)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("오아 눈 마사지기 저렴하게 팝니다.")
                .setContext("이거 쓰고 광명 찾았습니다. 꼭 써보세요.")
                .setItemName("오아 눈 마사지기")
                .addImageFilename("geonhee_life5_1.jpeg")
                .addImageFilename("geonhee_life5_2.jpeg")
                .addImageFilename("geonhee_life5_3.jpeg")
                .setPrice(30000)
                .setCategoryType(CategoryType.생활가전)
                .setDepartmentType(DepartmentType.예술학부)
                .setLocationType(LocationType.풋살장)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("미니언즈 볼펜 팔아요")
                .setContext("미니언즈 볼펜 팝니다.")
                .setItemName("미니언즈 볼펜")
                .addImageFilename("geonhee_pencil1_1.jpeg")
                .addImageFilename("geonhee_pencil1_2.jpeg")
                .addImageFilename("geonhee_pencil1_3.jpeg")
                .setPrice(5000)
                .setCategoryType(CategoryType.필기구)
                .setDepartmentType(DepartmentType.크리에이티브인문학부)
                .setLocationType(LocationType.상관없음)
                .build();


        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("마일드라이너 형광펜 10개 8000원")
                .setContext("색깔 잘 나옵니다.")
                .setItemName("마일드라이너 형광펜")
                .addImageFilename("geonhee_pencil2_1.jpeg")
                .addImageFilename("geonhee_pencil2_2.jpeg")
                .setPrice(8000)
                .setCategoryType(CategoryType.필기구)
                .setDepartmentType(DepartmentType.상상력인재학부)
                .setLocationType(LocationType.미래관)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("씰스티커 5종")
                .setContext("다꾸 좋아하시면 사서 써보세요!")
	.setItemName("씰스티커")
                .addImageFilename("geonhee_pencil3_1.jpeg")
                .setPrice(4000)
                .setCategoryType(CategoryType.필기구)
                .setDepartmentType(DepartmentType.크리에이티브인문학부)
                .setLocationType(LocationType.상상관)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("펜더 연필꽂이 팔아요")
                .setContext("귀여운 펜더로 책상을 깔끔하게 꾸며보세요!")
	.setItemName("연필꽂이")
                .addImageFilename("geonhee_pencil4_1.jpeg")
                .addImageFilename("geonhee_pencil4_2.jpeg")
                .setPrice(4000)
                .setCategoryType(CategoryType.필기구)
                .setDepartmentType(DepartmentType.크리에이티브인문학부)
                .setLocationType(LocationType.미래관)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("산리오 연필 12자루 5000원")
                .setContext("마이멜로디, 쿠로미, 시나몬롤, 폼폼푸린 있습니다.")
                .setItemName("연필")
                .addImageFilename("geonhee_pencil5_1.jpeg")
                .addImageFilename("geonhee_pencil5_2.jpeg")
                .addImageFilename("geonhee_pencil5_3.jpeg")
                .setPrice(5000)
                .setCategoryType(CategoryType.필기구)
                .setDepartmentType(DepartmentType.크리에이티브인문학부)
                .setLocationType(LocationType.상상빌리지)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("코듀로이 셔츠 팝니다.")
                .setContext("겨울 옷 정리하는 김에 하나 팝니다. 데일리로 무난하게 좋아요.")
                .setItemName("셔츠")
                .addImageFilename("geonhee_clothes1_1.jpeg")
                .setPrice(55000)
                .setCategoryType(CategoryType.의류)
                .setDepartmentType(DepartmentType.크리에이티브인문학부)
                .setLocationType(LocationType.상상관)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("반팔티 급처.")
                .setContext("작년에 예뻐보여서 샀는데 올해 보니까 영 아니라서 팔아요.")
                .setItemName("반팔티")
                .addImageFilename("geonhee_clothes2_1.jpeg")
                .addImageFilename("geonhee_clothes2_2.jpeg")
                .setPrice(2000)
                .setCategoryType(CategoryType.의류)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.공학관)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("귀염뽀짝 신발 팔아여")
                .setContext("샀는데 생각보다 신발이 작아서 팝니다. 230이고 한 번도 안 신었어요")
                .setItemName("신발")
                .addImageFilename("geonhee_clothes3_1.jpeg")
                .addImageFilename("geonhee_clothes3_2.jpeg")
                .setPrice(47000)
                .setCategoryType(CategoryType.의류)
                .setDepartmentType(DepartmentType.크리에이티브인문학부)
                .setLocationType(LocationType.상상빌리지)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("아디다스 볼캡 급처")
                .setContext("데일리로 무난하게 좋습니다.")
                .setItemName("아디다스 볼캡")
                .addImageFilename("geonhee_clothes4_1.jpeg")
                .addImageFilename("geonhee_clothes4_2.jpeg")
                .setPrice(13000)
                .setCategoryType(CategoryType.의류)
                .setDepartmentType(DepartmentType.예술학부)
                .setLocationType(LocationType.상상관)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("점프슈트 팔아용")
                .setContext("시원하고 편해요. 네고 없어용")
                .setItemName("점프슈트")
                .addImageFilename("geonhee_clothes5_1.jpeg")
                .setPrice(89000)
                .setCategoryType(CategoryType.의류)
                .setDepartmentType(DepartmentType.글로벌패션산업학부)
                .setLocationType(LocationType.미래관)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("샤넬 핸드크림 5.5")
	.setContext("향 좋고 보습력 좋습니다. 원래 8만원 짜리인데 5.5에 팔아요")
	.setItemName("샤넬 핸드크림")
                .addImageFilename("geonhee_beauty1_1.jpeg")
                .addImageFilename("geonhee_beauty1_2.jpeg")
                .setPrice(55000)
                .setCategoryType(CategoryType.뷰티미용)
                .setDepartmentType(DepartmentType.크리에이티브인문학부)
                .setLocationType(LocationType.상상빌리지)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("클린 웜코튼 향수 1/3 정도 썼어요")
                .setContext("제목대로입니다.")
                .setItemName("향수")
                .addImageFilename("geonhee_beauty2_1.jpeg")
                .setPrice(55000)
                .setCategoryType(CategoryType.뷰티미용)
                .setDepartmentType(DepartmentType.	ICT디자인학부)
                .setLocationType(LocationType.풋살장)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("미스치프 지샥 콜라보 손목시계")
                .setContext("생활기스 거의 없고 배터리 충분합니다.")
                .setItemName("손목시계")
                .addImageFilename("geonhee_beauty3_1.jpeg")
                .addImageFilename("geonhee_beauty3_2.jpeg")
                .addImageFilename("geonhee_beauty3_3.jpeg")
                .setPrice(85000)
                .setCategoryType(CategoryType.뷰티미용)
                .setDepartmentType(DepartmentType.기계전자공학부)
                .setLocationType(LocationType.상상빌리지)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("루이비통 모노그램 이클립스 반지")
                .setContext("반지 예뻐요")
                .setItemName("루이비통 반지")
                .addImageFilename("geonhee_beauty4_1.jpeg")
                .addImageFilename("geonhee_beauty4_2.jpeg")
                .setPrice(380000)
                .setCategoryType(CategoryType.뷰티미용)
                .setDepartmentType(DepartmentType.뷰티디자인매니지먼트학과)
                .setLocationType(LocationType.상관없음)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("쿨톤 화장품 급처 :)")
	.setContext("화장품 정리하는 김에 급처해요!")
	.setItemName("화장품")
                .addImageFilename("geonhee_beauty5_1.jpeg")
                .addImageFilename("geonhee_beauty5_2.jpeg")
                .addImageFilename("geonhee_beauty5_3.jpeg")
                .setPrice(25000)
                .setCategoryType(CategoryType.뷰티미용)
                .setDepartmentType(DepartmentType.크리에이티브인문학부)
                .setLocationType(LocationType.상관없음)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("한성대 마우스패드 팔아요")
                .setContext("샀는데 단패드라 필요가 없네요")
                .setItemName("마우스패드")
                .addImageFilename("geonhee_bugi1_1.png")
                .setPrice(5000)
                .setCategoryType(CategoryType.부기굿즈)
                .setDepartmentType(DepartmentType.크리에이티브인문학부)
                .setLocationType(LocationType.상관없음)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("상상부기 담요 팔아요")
                .setContext("따뜻하고 부기가 귀여워요")
                .setItemName("상상부기 담요")
                .addImageFilename("geonhee_bugi2_1.png")
                .setPrice(15000)
                .setCategoryType(CategoryType.부기굿즈)
                .setDepartmentType(DepartmentType.크리에이티브인문학부)
                .setLocationType(LocationType.상상관)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("부기 텀블러 팔아요! 700ml")
	.setContext("깨끗하게 썼고 생각보다 용량 커요")
                .setItemName("부기 텀블러")
                .addImageFilename("geonhee_bugi3_1.jpeg")
                .setPrice(8000)
                .setCategoryType(CategoryType.부기굿즈)
                .setDepartmentType(DepartmentType.컴퓨터공학부)
                .setLocationType(LocationType.공학관)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("부기굿즈 슬리퍼 270")
                .setContext("생각보다 푹신하고 튼튼해요")
                .setItemName("슬리퍼")
                .addImageFilename("geonhee_bugi4_1.png")
                .addImageFilename("geonhee_bugi4_2.jpeg")
                .setPrice(15000)
                .setCategoryType(CategoryType.부기굿즈)
                .setDepartmentType(DepartmentType.크리에이티브인문학부)
                .setLocationType(LocationType.풋살장)
                .build();

        new PostBuilder().setWhoPosted(gunhee)
                .setTitle("상상부기 포스트잇 팝니다")
                .setContext("다꾸 목적으로 샀던건데 생각보다 꾸준히 안 해서 그냥 팔아요 몇 장 안 썼어요")
                .setItemName("포스트잇")
                .addImageFilename("geonhee_bugi5_1.png")
                .setPrice(3000)
                .setCategoryType(CategoryType.부기굿즈)
                .setDepartmentType(DepartmentType.크리에이티브인문학부)
                .setLocationType(LocationType.낙산관)
                .build();




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

//        makeHardCodePost(minkyu, "에어팟 팔아요~", "테스트", "에어팟", "webBook.png", 100000, CategoryType.전자기기, DepartmentType.AI응용학과, LocationType.공학관);
        // 민규 전자기기 하드 코딩 7개
        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("에어팟 프로 1세대, air pods pro")
                .setContext("air pods pro 1세대 팝니다!")
                .setItemName("에어팟")
                .addImageFilename("minkyu0.jpeg")
                .addImageFilename("minkyu1.jpeg")
                .setPrice(108000)
                .setCategoryType(CategoryType.전자기기)
                .setDepartmentType(DepartmentType.AI응용학과)
                .setLocationType(LocationType.공학관)
                .build();

        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("National Geograpic air pod hard case")
                .setContext("에어팟 하드 케이스 정품 2만원짜리 10000에\n" +
                        "쿨거래 하실분?")
                .setItemName("에어팟")
                .addImageFilename("minkyu2.jpeg")
                .addImageFilename("minkyu3.jpeg")
                .setPrice(10000)
                .setCategoryType(CategoryType.전자기기)
                .setDepartmentType(DepartmentType.AI응용학과)
                .setLocationType(LocationType.공학관)
                .build();

        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("Air pods max")
                .setContext("상처없고 깨끗합니다\n" +
                        "구매하고 집에서만 몇번 사용해서 외관은\n" +
                        "새거와 다르지 않아요")
                .setItemName("에어팟")
                .addImageFilename("minkyu4.jpeg")
                .addImageFilename("minkyu5.jpeg")
                .setPrice(450000)
                .setCategoryType(CategoryType.전자기기)
                .setDepartmentType(DepartmentType.AI응용학과)
                .setLocationType(LocationType.공학관)
                .build();

        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("에어팟 맥스(Airpods Max) 스그")
                .setContext("22년 초쯤에 선물 받았는데\n" +
                        "거의 사용하지 않아 판매합니다\uD83E\uDD79\n" +
                        "\n" +
                        "기스 없고 외관상 흠잡을 곳이 없습니다!\n" +
                        "기능적으로는 문제 없는 것이 당연하구요!\n" +
                        "그만큼도 안 썼기 때무니죠,,, \uD83E\uDD79\n" +
                        "실사용 시간은 60시간도 안될 겁니다~\n" +
                        "\n" +
                        "박스있고 안에 구성품은 미사용입니다!\n" +
                        "하지만 에어팟 커버? 집? 이 없어요 그 검은색 ㅠㅠ\n" +
                        "\n" +
                        "직거래는 이매/야탑/서현까지 조율 가능합니다!\n" +
                        "택배비는 +3000원 우체국입니다~")
                .setItemName("에어팟 맥스")
                .addImageFilename("minkyu6.jpeg")
                .addImageFilename("minkyu7.jpeg")
                .addImageFilename("minkyu8.jpeg")
                .setPrice(400000)
                .setCategoryType(CategoryType.전자기기)
                .setDepartmentType(DepartmentType.AI응용학과)
                .setLocationType(LocationType.공학관)
                .build();

        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("에어팟 프로1 풀셋 Apple Air Pod Pro 1")
                .setContext("구매후 거의 쓰지않은 에어팟 프로1 입니다.\n" +
                        "\n" +
                        "케이스 포함이며 모든 유닛 정상 작동합니다.\n" +
                        "\n" +
                        "구매후 항상 케이스에 보관하고 있어 생활기스도 거의 없어요.\n" +
                        "\n" +
                        "본체와 왼쪽과 오른쪽 유닛외 다른 옵션은 없어요.\n" +
                        "\n" +
                        "필요하신 분께서 빠르게 가져가셔서 유용하게 쓰세요.\n" +
                        "\n" +
                        "선정릉역에서 직거래합니다\n" +
                        "\n" +
                        "감사합니다")
                .setItemName("에어팟")
                .addImageFilename("minkyu9.jpeg")
                .addImageFilename("minkyu10.jpeg")
                .addImageFilename("minkyu11.jpeg")
                .addImageFilename("minkyu12.jpeg")
                .setPrice(110000)
                .setCategoryType(CategoryType.전자기기)
                .setDepartmentType(DepartmentType.AI응용학과)
                .setLocationType(LocationType.공학관)
                .build();

        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("airpods pro 에어팟프로 케이스 새제품(개당)")
                .setContext("airpods pro 에어팟프로 케이스 새제품\n" +
                        "에어팟프로 케이스 새제품\n" +
                        "마직막 사진 고양이 케이스 판매입니다\n" +
                        "착용사진보시면 딱 맞게 들어갑니다~\n" +
                        "묵직한 느낌이구요, 짱짱해서 보호잘됩니다\n" +
                        "팟프로입니다~ 잘확인하시고 구매하세요\n" +
                        "부경대정문 거래희망합니다\n" +
                        "(강아지케이스 판매완료)")
                .setItemName("에어팟 pro")
                .addImageFilename("minkyu13.jpeg")
                .addImageFilename("minkyu14.jpeg")
                .addImageFilename("minkyu15.jpeg")
                .setPrice(6000)
                .setCategoryType(CategoryType.전자기기)
                .setDepartmentType(DepartmentType.AI응용학과)
                .setLocationType(LocationType.공학관)
                .build();

        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("에즈원아이피씨 KBD_82UT 키보드 ")
                .setContext("44,000원짜리 15,000원에 드립니다.")
                .setItemName("에어팟")
                .addImageFilename("minkyu16.jpeg")
                .addImageFilename("minkyu17.jpeg")
                .setPrice(15000)
                .setCategoryType(CategoryType.전자기기)
                .setDepartmentType(DepartmentType.AI응용학과)
                .setLocationType(LocationType.공학관)
                .build();

        // 도서 7개

        // 필기구 7개
        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("휴고보스 필기구 세트 새상품")
                .setContext("BOSS 휴고보스 볼펜 노트 세트 새상품\n" +
                        "박스에 세트로 담겨 있습니다")
                .setItemName("필기구 세트")
                .addImageFilename("minkyu18.jpeg")
                .addImageFilename("minkyu19.jpeg")
                .setPrice(45000)
                .setCategoryType(CategoryType.필기구)
                .setDepartmentType(DepartmentType.AI응용학과)
                .setLocationType(LocationType.공학관)
                .build();
        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("스테들러 필기구 4종 멀티세트")
                .setContext("스테들러 4종 멀티세트 입니다.\n" +
                        "살 때 인터넷 최저가로 개당 5천원에 주고 샀어요~\n" +
                        "구성품 확인해보시라고 인터넷상의 제품설명 사진에 첨부했어요~\n" +
                        "6개 중 2개 파로 4개 남았습니다\n" +
                        "개당 2500원에 팔고 4개 모두 한꺼번에 구매하시면 9000원에 드릴게요~\n" +
                        "\n" +
                        "부명초 앞 직거래 가능합니다\n" +
                        "(사정상 평일 오후 8시 이후, 주말 아무 때나 가능)")
                .setItemName("필기구")
                .addImageFilename("minkyu20.jpeg")
                .setPrice(2500)
                .setCategoryType(CategoryType.필기구)
                .setDepartmentType(DepartmentType.AI응용학과)
                .setLocationType(LocationType.공학관)
                .build();
        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("필기구 팝니당")
                .setContext("이제 공부할일이 없어서 다 팝니다")
                .setItemName("필기구")
                .addImageFilename("minkyu21.jpeg")
                .setPrice(5000)
                .setCategoryType(CategoryType.필기구)
                .setDepartmentType(DepartmentType.AI응용학과)
                .setLocationType(LocationType.공학관)
                .build();
        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("필기구 일괄 판매합니다")
                .setContext("궁금하신점은 문자주세요")
                .setItemName("필기구")
                .addImageFilename("minkyu22.jpeg")
                .addImageFilename("minkyu23.jpeg")
                .setPrice(60000)
                .setCategoryType(CategoryType.필기구)
                .setDepartmentType(DepartmentType.AI응용학과)
                .setLocationType(LocationType.공학관)
                .build();
        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("몽블랑 필기구와 장식품")
                .setContext("몽블랑에서 나온 소소한 학용품들입니다.\n" +
                        "\n" +
                        "지우개 + 자 + 연필 3 자루 + 인형 입니다.\n" +
                        "인형은 등에 클래식 만년필을 지고 있는데 디테일도 있고 참 예쁩니다.\n" +
                        "\n" +
                        "수원시청역 직거래 가능합니다.")
                .setItemName("필기구")
                .addImageFilename("minkyu24.jpeg")
                .setPrice(23000)
                .setCategoryType(CategoryType.필기구)
                .setDepartmentType(DepartmentType.AI응용학과)
                .setLocationType(LocationType.공학관)
                .build();
        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("고시용 필기구의 최강자! 에너겔 메탈팁 45개 판매")
                .setContext("고시용 필기구에 최강자 펜텔 에너겔 메탈팁\n" +
                        "검정 39, 빨강 3, 파랑 3\n" +
                        "총 45자루입니다\n" +
                        "\n" +
                        "펜 팁 그대로 붙어 있는 새것 입니다\n" +
                        "\n" +
                        "자루당 1,000원 입니다\n" +
                        "\n" +
                        "연락주세요^^")
                .setItemName("필기구")
                .addImageFilename("minkyu25.jpeg")
                .addImageFilename("minkyu26.jpeg")
                .setPrice(45000)
                .setCategoryType(CategoryType.필기구)
                .setDepartmentType(DepartmentType.AI응용학과)
                .setLocationType(LocationType.공학관)
                .build();
        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("크로스 볼펜 필기구 마블시리즈 헐크 cross rolling ball pen marvel hulk")
                .setContext("cross rolling ball pen\n" +
                        "\n" +
                        "크로스 볼펜입니다.\n" +
                        "마블 헐크 모델입니다.\n" +
                        "새것과 같은 상태입니다.\n" +
                        "\n" +
                        "궁금하신 점은 전화주세요.")
                .setItemName("필기구")
                .addImageFilename("minkyu27.jpeg")
                .addImageFilename("minkyu28.jpeg")
                .addImageFilename("minkyu29.jpeg")
                .setPrice(80000)
                .setCategoryType(CategoryType.필기구)
                .setDepartmentType(DepartmentType.AI응용학과)
                .setLocationType(LocationType.공학관)
                .build();

        // 생활가전 7개

        // 부기 굿즈 7개

        // 뷰티 미용 7개
        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("메이크업자격증, 미용자격증, 미용재료")
                .setContext("연습용으로 사용하기 좋아요\n" +
                        "메이크업 자격증 할 때 사용했어요~")
                .setItemName("미용 용품")
                .addImageFilename("minkyu30.jpeg")
                .setPrice(25000)
                .setCategoryType(CategoryType.뷰티미용)
                .setDepartmentType(DepartmentType.뷰티디자인매니지먼트학과)
                .setLocationType(LocationType.상상관)
                .build();
        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("미용대 미용의자 미용실샴푸의자 팔아요")
                .setContext("작년에 중고로 40만원에 구매했어요 ~\n" +
                        "\n" +
                        "어머님 다리가 안 좋으셔서 몇 번 사용했다가 회복하셔서 판매합니다^^\n" +
                        "\n" +
                        "수도꼭지는 빼버려서 철물점에서 냉온수 가능한 걸로 따로구매하셔야 할 것 같아 싸게 올려둡니다^^\n" +
                        "\n" +
                        "직접 가져가셔야 하고요 SUV 차량엔 실릴 것 같네요 ~\n" +
                        "\n" +
                        "곤지암 오향리 입니다")
                .setItemName("미용 용품")
                .addImageFilename("minkyu31.jpeg")
                .addImageFilename("minkyu32.jpeg")
                .addImageFilename("minkyu33.jpeg")
                .setPrice(70000)
                .setCategoryType(CategoryType.뷰티미용)
                .setDepartmentType(DepartmentType.뷰티디자인매니지먼트학과)
                .setLocationType(LocationType.상상관)
                .build();
        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("강아지미용가위 바리깡 미용 해먹거치대 나들이띠 합")
                .setContext("강아지입양받아 미용시키려고발등 구매하여 2회사용하고 다른데로 분양보내면서 필요없어져 새제품이나 다름없어요 필요하신분 직거래로 연락바람니다 사진참고하시면됩니다")
                .setItemName("미용 용품")
                .addImageFilename("minkyu34.jpeg")
                .addImageFilename("minkyu35.jpeg")
                .addImageFilename("minkyu36.jpeg")
                .setPrice(10000)
                .setCategoryType(CategoryType.뷰티미용)
                .setDepartmentType(DepartmentType.뷰티디자인매니지먼트학과)
                .setLocationType(LocationType.창의관)
                .build();
        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("향수 일괄로 싸게 처분합니다 포맨트 코튼허그 비레디 보이블랙")
                .setContext("포맨트 코튼허그 50ml\n" +
                        "비레디 보이블랙 50ml\n" +
                        "일괄 사용감 10회미만이고 상태최상 입니다\n" +
                        "동생이 향수를 쓰지않아 싸게 처분합니다\n" +
                        "일괄로만 판매합니다")
                .setItemName("뷰티미용")
                .addImageFilename("minkyu37.jpeg")
                .setPrice(45000)
                .setCategoryType(CategoryType.뷰티미용)
                .setDepartmentType(DepartmentType.뷰티디자인매니지먼트학과)
                .setLocationType(LocationType.창의관)
                .build();
        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("빅토르앤롤프 플라워밤 향수+봉봉 오드 향수")
                .setContext("구매해서 10번도 안뿌린 것 같아요!\n" +
                        "향 좋습니다!!!! 근데 향수정리하면서 향수가 많아서 이 친구도 보내주려 합니다...\n" +
                        "플러워밤(본품) + 봉봉(미니) 다 드립니다! 박스도 있어요!\n" +
                        "\n" +
                        "신대방삼거리역 거래가능\n" +
                        "문고리 거래가능\n" +
                        "네고가능")
                .setItemName("필기구")
                .addImageFilename("minkyu38.jpeg")
                .addImageFilename("minkyu39.jpeg")
                .setPrice(30000)
                .setCategoryType(CategoryType.뷰티미용)
                .setDepartmentType(DepartmentType.뷰티디자인매니지먼트학과)
                .setLocationType(LocationType.창의관)
                .build();
        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("크리드 향수공병 톰포드 향수공병")
                .setContext("크리드 어벤투스 100ml 20,000\n" +
                        "톰포드 네롤리 50ml 20,000\n" +
                        "크리드 실버마운틴 75ml 20,000")
                .setItemName("향수")
                .addImageFilename("minkyu40.jpeg")
                .setPrice(20000)
                .setCategoryType(CategoryType.뷰티미용)
                .setDepartmentType(DepartmentType.뷰티디자인매니지먼트학과)
                .setLocationType(LocationType.창의관)
                .build();
        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("입생로랑 몽파리 오 드 뚜왈렛 루미에르 향수 30 미리 입생향수")
                .setContext("선물받구 안써서 내놔요")
                .setItemName("향수")
                .addImageFilename("minkyu41.jpeg")
                .addImageFilename("minkyu42.jpeg")
                .addImageFilename("minkyu43.jpeg")
                .setPrice(65000)
                .setCategoryType(CategoryType.뷰티미용)
                .setDepartmentType(DepartmentType.뷰티디자인매니지먼트학과)
                .setLocationType(LocationType.창의관)
                .build();



//        makeHardCodePost(minkyu, "운영체제 교재 팔아요!", "테스팅", "운영체제", 8000, CategoryType.도서, DepartmentType.컴퓨터공학부, LocationType.낙산관);
//
//        makeHardCodePost(minkyu, "웹프 교재 팝니다.", "파라요", "웹프로그래밍", 9000, CategoryType.도서, DepartmentType.컴퓨터공학부, LocationType.미래관);
//        setPostImageHard("webBook.png");


//        postRepository.savePost(post1);
//        postRepository.savePost(osBookPost);
//        postRepository.savePost(javaBookPost);
//        postRepository.savePost(macBookPost);
//        postRepository.savePost(webBookPost);
//        postRepository.savePost(bugiPost);
//        postRepository.savePost(airPodPost);

        // ======= 하드 코딩 데이터 이미지 설정 (랜덤 X) =======
//        Image osBookImage = new Image();
//        osBookImage.setImageFilename("osBook.png");
//        osBookImage.setPost(osBookPost);
//        imageRepository.save(osBookImage);
//
//        Image airPodImage = new Image();
//        airPodImage.setImageFilename("airpod.jpeg");
//        airPodImage.setPost(airPodPost);
//        imageRepository.save(airPodImage);

//        Image macBookImage = new Image();
//        macBookImage.setImageFilename("macbook.jpeg");
//        macBookImage.setPost(macBookPost);
//        imageRepository.save(macBookImage);
//
//        Image bugiImage = new Image();
//        bugiImage.setImageFilename("bugi.png");
//        bugiImage.setPost(bugiPost);
//        imageRepository.save(bugiImage);
//
//        Image javaBookImage = new Image();
//        javaBookImage.setImageFilename("javaBook.png");
//        javaBookImage.setPost(javaBookPost);
//        imageRepository.save(javaBookImage);
//
//        Image our_memory_image1 = new Image();
//        our_memory_image1.setImageFilename("miss1.png");
//        our_memory_image1.setPost(post1);
////        our_memory_image1.setPost(post2);
//        imageRepository.save(our_memory_image1);

        // ======= 하드 코딩 데이터 이미지 설정 (랜덤 X) ======

//        List<Image> images = new ArrayList<>();
//        images.add(our_memory_image1);
//        postService.savePost(post2);

//        post2.setImages(images);
//        macBookPost.setImages(images);
//        post4.setImages(images);

//        ChatRoom chatRoom = chatService.startChatRoomService(post2, post2.getWho_posted(), memberB);
//        chatService.startChatMessageService(chatRoom, memberB, "안녕하세요!");
//        // 그냥 테스트
//
//        ChatRoom chatRoom2 = chatService.startChatRoomService(post1, post4.getWho_posted(), memberB);
//        chatService.startChatMessageService(chatRoom2, memberB, "안녕하십니까?");

//        Post post34 = postService.findPostByPostId(post1.getPostId());
//        System.out.println("post34.getPostId() = " + post34.getPostId());
//        PostDetailResponse testing = new PostDetailResponse(post34);
//        System.out.println(testing);
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


    /*
    @PostConstruct
    public void init() {
        // 초기화 작업 수행
        Member memberA = new Member("memberA");
        memberA.setUsername("더미부기");
        memberRepository.save(memberA);

        Member memberB = new Member("memberB");
        memberB.setUsername("건희");
        memberRepository.save(memberB);

        Member memberC = new Member("memberC");
        memberC.setUsername("memberC");
        memberRepository.save(memberC);

        Member memberD = new Member("memberD");
        memberD.setUsername("post testing");
        memberRepository.save(memberD);

        // MemberA 가 새 게시글 등록
        Post postByMemberA = new Post();
        postByMemberA.setWho_posted(memberA);
        postDataJpaRepository.save(postByMemberA);

        // MemberB 가 새 게시글 등록
        Post postByMemberB = new Post();
        postByMemberB.setWho_posted(memberB);
        postDataJpaRepository.save(postByMemberB);

        // MemberB 가 MemberA의 게시글에서 문의하기 버튼을 누름
        ChatRoom chatRoom = chatService.startChatRoomService(postByMemberA, memberB);
        ChatRoom chatRoom1 = chatService.startChatRoomService(postByMemberA, memberC);

        // MemberB 가 채팅방에서 hello memberA 입력 후 전송 버튼을 누름
        ChatMessage chatMessage = chatService.startChatMessageService(chatRoom, memberB, "판매중이신가요?");
        ChatMessage chatMessage1 = chatService.startChatMessageService(chatRoom1, memberC, "hello memberA");

        // memberA 가 채팅방에서 hello memberB 답장
        chatService.startChatMessageService(chatRoom, memberA, "넵 아직 판매중입니다.");
        chatService.startChatMessageService(chatRoom, memberA, "무슨 일이신가요?");
        chatService.startChatMessageService(chatRoom, memberB, "아닙니다. 좋은 밤 되십시오.");
        chatService.startChatMessageService(chatRoom, memberA, "test2");
        chatService.startChatMessageService(chatRoom, memberB, "이어말하기 연습");
        chatService.startChatMessageService(chatRoom, memberB, "제발");


        // 해당 포스트에 등록된 채팅방 리스트를 불러온다
        List<ChatRoom> rooms = chatService.getChatRoomLists(postByMemberA.getPostId());
        System.out.println("rooms.size() = " + rooms.size());
        for (ChatRoom room : rooms) {
            System.out.println("room.getMember().getUsername() = " + room.getMember().getUsername());
        }

        // memberA 가 등록한 게시글에 대한 대화목록을 불러온다
        List<ChatMessage> chatLists = chatService.getChatLists(rooms.get(0).getId());

        for (ChatMessage message : chatLists) {
            System.out.println("message.getMember().getUsername() = " + message.getMember().getUsername());
            System.out.println("message.getText() = " + message.getMessage());
        }

        ChaRoomResponse chatResponse = new ChaRoomResponse(chatRoom.getPost(), chatMessage);

        System.out.println(chatResponse);

//        ChatRoom chatRoom = new ChatRoom();
//        chatRoom.setPost(post);
//        chatRoomRepository.save(chatRoom);
//
//        ChatMessage chatMessage = new ChatMessage();
//        chatMessage.setMember(memberB);
//        chatMessage.setText("hello memberA!");
//        chatMessage.setChatRoom(chatRoom);
//        chatMessageRepository.save(chatMessage);
    }
    */
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
        private LocalDateTime createdDate;


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
        public void generateRandomCreatedDate() {
            // Generate random month within the range of 1 (January) to 12 (December)
            int randomMonth = ThreadLocalRandom.current().nextInt(1, 13);

            // Generate random day within the range of 1 to 28 (assumes a non-leap year)
            int randomDay = ThreadLocalRandom.current().nextInt(1, 29);
            this.createdDate = LocalDateTime.of(2023, randomMonth, randomDay, 0, 0);
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

