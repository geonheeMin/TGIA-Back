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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
public class DataLoader {
    @Autowired
    private PostService postService;
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



        Member memberA = new Member("memberA");
        memberA.setUsername("건희");
        Member memberB = new Member("memberB");
        Member memberD = new Member("memberD");
        memberD.setUsername("아톰");
        memberB.setUsername("영식");
        Member memberC = new Member("memberC");
        memberC.setUsername("용기");

        Image image = new Image();
        image.setImageFilename("basicprofile.png");
        imageRepository.save(image);
        CategoryType[] categories = CategoryType.values();
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

        memberA.setFirstTrack(firstTrack);
        memberA.setSecondTrack(secondTrack);
        // memberA.setImage(image1);

        memberB.setFirstTrack(firstTrack1);
        memberB.setSecondTrack(secondTrack1);

        memberC.setFirstTrack(firstTrack1);
        memberC.setSecondTrack(secondTrack1);

        Image image1 = new Image();
        image1.setImageFilename("aaa.png");
        imageRepository.save(image1);
        memberC.setImage(image1);
        memberB.setImage(image1);
        memberA.setImage(image1);

        memberRepository.save(memberA);
        memberRepository.save(memberB);
        memberRepository.save(memberC);
        memberRepository.save(memberD);
        // user_id 가 memberA인 멤버의 트랙1: 웹공학트랙, 2트랙을 빅데이터트랙
        // 프론트에서 pk id가 4인 멤버의 트랙1, 2를 물어본다면?

        Category post1category = new Category();
        Category post2category = new Category();
        Category post3category = new Category();
        Category post4category = new Category();
        Category post5category = new Category();


        post1category.setCategory_type(CategoryType.도서);
        post2category.setCategory_type(CategoryType.생활가전);
        post3category.setCategory_type(CategoryType.부기굿즈);
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
        System.out.println("afdsfasdfasd" + post1.getPostId());
//        Post post1 = new Post("제목1","내용");
        Post post2 = new Post();
        post2.setPost_title("제발 좀 되라");
        post2.setPost_text("제발!");

        System.out.println("dfadfadf: " + post2.getPostId());

        Post post3 = new Post("제목3","내용");
        Post post4 = new Post("제목4","내용");
        post1.setWho_posted(memberA);
        post2.setWho_posted(memberA);
        post3.setWho_posted(memberB);
        post4.setWho_posted(memberD);

        post1.setPrice(10000);
        post2.setPrice(10000);
        post3.setPrice(10000);
        post4.setPrice(10000);

        //구매목록 하나 만들기
        // 멤버 a가올린 포스트를 멤버 b가 사면서 포스트에 구매 목록에 멤버가 멤버b로 바뀜
        Purchased purchased = new Purchased();
        purchased.setMember(memberB);
        purchasedRepository.save(purchased);
        post1.setPurchased(purchased);

        for (int i =0;i<30;i++) {
            Post dummyPost = new Post();

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
            dummyPost.setWho_posted(memberA);
            dummyPost.setPrice(10000);

            dummyPost.setDepartment(department);

            dummyPost.setLocationType(locations[random.nextInt(locations.length)]);
            dummyPost.setLocation_text("101호");

            dummyPost.setItem_name("MacBook Pro 13");
//            post2.setItem_name("아이폰 14 프로 맥스 실버 256GB");
//            post3.setItem_name("아이패드 에어 4세대 블루");
//            post4.setItem_name("토비의 스프링 1편");

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
//        our_memory_image1.getImageFilename();

//        Image our_memory_image2 = new Image();
//        our_memory_image2.setImageFilename("5558025b-a73f-4fcf-a0d7-87300924f989.jpg");
//        imageRepository.save(our_memory_image2);
//        our_memory_image2.getImageFilename();



//        List<Image> images = imageService.findImages(imageNames);


//        images.add(our_memory_image2);



//        Category post1category = new Category();
//        Category post2category = new Category();
//        Category post3category = new Category();
//        Category post4category = new Category();
//
//        post1category.setCategory_type(CategoryType.도서);
//        post2category.setCategory_type(CategoryType.생활가전);
//        post3category.setCategory_type(CategoryType.부기굿즈);
//        post4category.setCategory_type(CategoryType.전자기기);
//
//        categoryJpaRepository.save(post1category);
//        categoryJpaRepository.save(post2category);
//        categoryJpaRepository.save(post3category);
//        categoryJpaRepository.save(post4category);

        post1.setCategory(post1category);
        post2.setCategory(post2category);
        post3.setCategory(post3category);
        post4.setCategory(post4category);

        post1.setDepartment(department);
        post2.setDepartment(department);
        post3.setDepartment(department);
        post4.setDepartment(department);



        post1.setLocationType(LocationType.공학관);
        post1.setLocation_text("101호");
        post2.setLocationType(LocationType.미래관);
        post2.setLocation_text("102호");
        post3.setLocationType(LocationType.낙산관);
        post3.setLocation_text("103호");
        post4.setLocationType(LocationType.풋살장);
        post4.setLocation_text("104");

        post1.setItem_name("MacBook Pro 13");
        post2.setItem_name("아이폰 14 프로 맥스 실버 256GB");
        post3.setItem_name("아이패드 에어 4세대 블루");
        post4.setItem_name("토비의 스프링 1편");

        post1.setTrack(TrackType.게임그래픽디자인트랙);
        post1.setCollege(CollegeType.미래융합사회과학대학);
        Department department1 = new Department(DepartmentType.AI응용학과);
        post1.setDepartment(department1);


        post2.setTrack(TrackType.경제금융투자트랙);
        post2.setCollege(CollegeType.디자인대학);
        Department department2 = new Department(DepartmentType.문학문화콘텐츠학과);
        post2.setDepartment(department2);

        post3.setTrack(TrackType.기업경제분석트랙);
        post3.setCollege(CollegeType.미래플러스대학);
        Department department3 = new Department(DepartmentType.IT융합공학부);
        post3.setDepartment(department3);

        post4.setTrack(TrackType.글로벌비즈니스트랙);
        post4.setCollege(CollegeType.IT공과대학);
        Department department4 = new Department(DepartmentType.상상력인재학부);
        post4.setDepartment(department4);

        departMentJpaRepository.save(department1);
        departMentJpaRepository.save(department2);
        departMentJpaRepository.save(department3);
        departMentJpaRepository.save(department4);

        postRepository.savePost(post1);
        postRepository.savePost(post2);
        postRepository.savePost(post3);
        postRepository.savePost(post4);

        Image our_memory_image1 = new Image();
        our_memory_image1.setImageFilename("miss1.png");
        our_memory_image1.setPost(post1);
//        our_memory_image1.setPost(post2);
        imageRepository.save(our_memory_image1);

        List<Image> images = new ArrayList<>();
        images.add(our_memory_image1);
//        postService.savePost(post2);

        System.out.println("ddfadf" + post2.getPostId());

//        post2.setImages(images);
//        post3.setImages(images);
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
}