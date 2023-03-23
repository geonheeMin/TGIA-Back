package capstone.market.controller;

import capstone.market.domain.*;
import capstone.market.post_dto.PostForm;

import capstone.market.post_dto.PostListResponse;
import capstone.market.service.CategoryService;
import capstone.market.service.FileService;
import capstone.market.service.MemberService;
import capstone.market.service.PostService;
import capstone.market.session.SessionConst;
import capstone.market.session.SessionManager;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * react native 와 연동시 Controller 가 아닌 RestController 로 해야한다!
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    // post 를 작성한 Member 의 PK 를 알아내기 위해 memberService 사용
    private final MemberService memberService;
    private final CategoryService categoryService;


    private final SessionManager sessionManager;
    private final FileService fileService;

    //@@@@@@@@@@@@@@@@@카테고리로 포스트 필터링@@@@@@@@@@@@@@@@@@@ 3월 17일
    @GetMapping("/category")
    public List<PostListResponse> SearchByCategory(@RequestParam CategoryType category) {

        List<Post> posts = postService.SearchByCategory(category);


        List<PostListResponse> result = posts.stream()
                .map(p -> new PostListResponse(p))
                .collect(Collectors.toList());

        return result;
    }
    //@@@@@@@@@@@@@@@@@카테고리로 포스트 필터링@@@@@@@@@@@@@@@@@@@ 3월 17일




    //@@@@@@@@@@@@@@@@@포스트 제목으로 검색하기 추가@@@@@@@@@@@@@@@@@@@ 3월 15일
    @GetMapping("/search")
        public List<PostListResponse> findByTitleContaining(@RequestParam String keyword) {

        List<Post> posts = postService.findByTitleContaing(keyword);


        List<PostListResponse> result = posts.stream()
                .map(p -> new PostListResponse(p))
                .collect(Collectors.toList());

        return result;
    }
    //@@@@@@@@@@@@@@@@@포스트 제목으로 검색하기 추가@@@@@@@@@@@@@@@@@@@ 3월 15일
    // 3월 18일 추가
    // 게시물을 수정하기 위한 기존 정보 가져온다.
    @GetMapping("post/edit")
    public PostForm updateItemForm(Long post_id) {
        Post post = postService.findPostByPostId(post_id);

        // form 을 업데이트 하는데 이 PostForm 을 보낸다 entity 가 아니라
        PostForm editPost = new PostForm();
//        editPost.setId(post.getPostId());
        editPost.setPrice(post.getPrice());
        editPost.setTitle(post.getPost_title());
        editPost.setContent(post.getPost_text());
        return editPost;
    }

    // 3월 18일 추가
    // 게시글 수정
    @PutMapping("post/edit")
    public PostForm updatePost(@RequestBody PostForm request) {
        postService.update(request);
        return new PostForm(request);
    }

    //@GetMapping("/post/list")
    public List<Post> postListV3(HttpServletRequest request) {
        log.info("@GetMapping(\"/post/list\")");
        // 세션 관리자에 저장된 회원 정보 조회
        while (!SessionConst.POST_ENDED) {
            log.info("WHILE");
        }

        log.info("BREAK");

        HttpSession session = request.getSession(false);

        if (session == null) {
            log.info("session error");
        }

        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if (member == null) {
            log.info("login error");
        }

        log.info("hello world" + member.getUser_id());
        List<Post> posts = postService.findPostByUserId(member.getUser_id());
        return posts;
    }

    // @GetMapping("/post/list") // 2.17
    // + 이미지 정보 추가 3월 17일
    public List<PostListResponse> postListV4(String user_id) {
        log.info("@GetMapping(\"/post/list\")");
//        while(!SessionConst.POST_ENDED) {
//            log.info("WHILE");
//        }
        log.info("BREAK");
        log.info("user_id = {}", user_id);

        List<Post> posts = postService.findPostByUserId(user_id);

        List<PostListResponse> result = posts.stream()
                .map(p -> new PostListResponse(p))
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping("/post/my_list") // 2.17 -> 판매 목록
    public List<PostListResponse> findMyPostList(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        log.info("COOKIE! = {}", member.getUser_id());

        List<Post> posts = postService.findPostByUserId(member.getUser_id());

        List<PostListResponse> result = posts.stream()
                .map(p -> new PostListResponse(p))
                .collect(Collectors.toList());

        return result;
    }

    //PostDetailResponse 이걸로 추후 바꿔야함 PostListResponse이거 대신에
//    @GetMapping("/post/list") // 2.17
//    public List<PostListResponse> postListV5(HttpServletRequest request) {
//
//        HttpSession session = request.getSession(false);
//
//        List<Post> posts = postService.findAll();
//
//        List<PostListResponse> result = posts.stream()
//                .map(p -> new PostListResponse(p))
//                .collect(Collectors.toList());
//
//        return result;
//    }

    @GetMapping("/post/list_all") // 2.17
    public List<PostListResponse> postListV6() {

        List<Post> posts = postService.findAll();

        List<PostListResponse> result = posts.stream()
                .map(p -> new PostListResponse(p))
                .collect(Collectors.toList());

        return result;
    }

    // 3월 17일 프론트와 연동 시 Image 테이블과 Post 테이블 매핑 문제 해결
    @PostMapping("/post/insert")
    public void postAdd (@RequestBody AddPostRequest request) {
        Post post = new Post();

        log.info("request_info = {}", request.getUser_id());
        log.info("request_info.title = {}", request.getTitle());
        post.setWho_posted(memberService.findOne(request.getUser_id()));
        post.setPost_title(request.title);
        post.setPost_text(request.content);
        post.setPrice(request.price);
        Category category = new Category();
        categoryService.UpdateCategory(category,request.getCategory());
        post.setCategory(category);
//        post.setImage(fileService.findImageFilename(request.image_file_name));
        postService.savePost(post);
    }

    // 게시물 상세 구현 2월 21일
    // + 가격 추가 3월 3일
    @GetMapping("/post/details")
    public PostDetailResponse postDetails(@RequestParam Long postId) {
        Post post = postService.findPostByPostId(postId);
        return new PostDetailResponse(post);

    }

    //테스트용@@@@@@2
    @GetMapping("/post/all")
    public  List<PostDetailResponse> postDetails2() {
        List<Post> all = postService.findAll();

        List<PostDetailResponse> result = all.stream()
                .map(p -> new PostDetailResponse(p))
                .collect(Collectors.toList());

        return result;

    }


    


    //테스트용@@@@@@2









    // 찜 목록 구현 2월 21일
//    @PostMapping("/post/liked")
//    public void postLike(Long post_id, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
//
//        memberService.addLiked(post_id, member.getId());
//    }

//    @GetMapping("/post/liked")
//    public List<PostLikedResponse> postLikedList() {
//
//    }

    // 게시물 상세 화면을 위한 dto
    @Data
    static class PostDetailResponse {
        private Long post_id;
        private String title;
        private String username;
        private CategoryType category;
        private String text;
        private Integer price;
        private List<String> images = new ArrayList<>();

        public PostDetailResponse(Post post) {
            this.post_id = post.getPostId();
            this.title = post.getPost_title();
            this.username = post.getWho_posted().getUsername();
//            this.category = post.getCategory().getCategory_type();
            this.text = post.getPost_text();
            this.price = post.getPrice();
            if (post.getImages().isEmpty()) {
                images.add("hello world");
            } else {
                for (Image image : post.getImages()) {
                    images.add(image.getImageFilename());
                }
            }
        }
    }

    // 찜목록을 위한 dto
//    static class PostLikedResponse {
//        private
//    }

    // 게시글 추가를 위한 FORM 객체

    /**
     * params: {
     *   title: title,
     *   user: id,
     *   category: category,
     *   content: content,
     *   date: time,
     *  },
     */
    @Data
    static class AddPostRequest {
        private String title;
        private Long user_id;
        private CategoryType category;
        private String content;
//        private String time;
        private Integer price;
//        private String image_file_name;
    }
    //
    @Data
    static class ForUserId {
        private String user_id;
    }


//    @GetMapping("/post/list")
//    public PostListResponse postList() {
//        postService.findPostByUserId();
//    }
//
//    public Member findMe() {
//
//    }
}
