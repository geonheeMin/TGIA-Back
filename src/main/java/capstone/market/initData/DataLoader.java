package capstone.market.initData;

import capstone.market.domain.*;
import capstone.market.repository.*;
import capstone.market.service.ChatService;
import capstone.market.service.ImageService;
import capstone.market.service.PostService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.type.LocalDateTimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
        Purchased purchased = new Purchased();
        purchased.setMember(gunhee);
        purchasedRepository.save(purchased);
        post1.setPurchased(purchased);

//        Purchased purchased2 = new Purchased();
//        purchased2.setMember(memberC);
//        purchasedRepository.save(purchased2);
//        macBookPost.setPurchased(purchased2);

        CollegeType[] collegeTypes = CollegeType.values();
        for (int i =0;i<30;i++) {
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
        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("에어팟 팔아요")
                .setContext("test")
                .setItemName("에어팟")
                .setImageFilename("webBook.png")
                .setPrice(10000)
                .setCategoryType(CategoryType.전자기기)
                .setDepartmentType(DepartmentType.AI응용학과)
                .setLocationType(LocationType.공학관)
                .build();

        new PostBuilder().setWhoPosted(minkyu)
                .setTitle("에어팟 팔아요")
                .setContext("test")
                .setItemName("에어팟")
                .setImageFilename("webBook.png")
                .setPrice(10000)
                .setCategoryType(CategoryType.전자기기)
                .setDepartmentType(DepartmentType.AI응용학과)
                .setLocationType(LocationType.공학관)
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
        private String imageFilename;
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

        public PostBuilder setImageFilename(String imageFilename) {
            this.imageFilename = imageFilename;
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

            Image imageHard = new Image();
            imageHard.setImageFilename(imageFilename);
            imageHard.setPost(hardcodePost);
            imageRepository.save(imageHard);
        }
    }

}

