//package capstone.market.controller;
//
//import capstone.market.domain.Favorite;
//import capstone.market.domain.Member;
//import capstone.market.domain.Post;
//import capstone.market.service.FavoriteService;
//import capstone.market.service.MemberService;
//import capstone.market.service.PostService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@Slf4j
//@RequiredArgsConstructor
//public class FavoriteController {
//
//    private final FavoriteService favoriteService;
//    private final MemberService memberService;
//    private final PostService postService;
//
//    //좋아요가 있는지 없는 지  있으면 1 없으면 0
//
//    @GetMapping("/profile/favorite")   //좀 애매 하네 어디서든 지 포스트 검색 할 수 있음
//    public Integer IsFavorite(@RequestParam("postId") Long PostId, @RequestParam("userId") Long userId) {
//        Member user = memberService.findOne(userId);
//        Post post = postService.findPostByPostId(PostId);
//
//        List<Favorite> favorites = user.getFavorites(); // 유저의 좋아요 리스트 가져온다.
//        for(Favorite favorite : favorites){
//            Long postId = favorite.getPost().getPostId();
//          if(postId.equals(PostId))  {
//              return 1;
//          }
//        }
//        return 0;
//
//    }
//
//    //좋아요 추가
//    @PostMapping("/profile/favorite")   //좀 애매 하네 어디서든 지 포스트 검색 할 수 있음
//    public void addFavorite(@RequestParam Long postId, @RequestParam Long userId) {
//
//          Member user = memberService.findOne(userId);
//          Post post = postService.findPostByPostId(postId);
//          Favorite favorite = new Favorite(user, post);
//          user.getFavorites().add(favorite);
//          favoriteService.save(favorite);
//    }
//
//    //좋아요 삭제
//    @DeleteMapping("/profile/favorite")   //좀 애매 하네 어디서든 지 포스트 검색 할 수 있음
//    public void deleteFavorite(@RequestParam Long favoriteId) {
//        favoriteService.delete(favoriteId);
//
//    }
//
//    //내 좋아요 리스트 => 좋아요가 선택된 리스트
//    @GetMapping("/profile/favorite")
//    public List<PostController.PostListResponse> listFavorite(@RequestParam("userId") Long userId) {
//        Member user = memberService.findOne(userId);
//        List<Favorite> favorites = user.getFavorites();
//
//        List<Post> posts = new ArrayList<>();
//
//        for(Favorite favorite : favorites){
//
//            Long postId = favorite.getPost().getPostId();
//
//            posts.add(postService.findPostByPostId(postId));
//
//        }
//                List<PostController.PostListResponse> result = posts.stream()
//                .map(p -> new PostController.PostListResponse(p))
//                .collect(Collectors.toList());
//
//        return result;
//    }
//
//}
