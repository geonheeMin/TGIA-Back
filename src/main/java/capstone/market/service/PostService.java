package capstone.market.service;

import capstone.market.domain.CategoryType;
import capstone.market.domain.Post;
import capstone.market.domain.Purchased;
import capstone.market.post_dto.PostForm;
import capstone.market.profile_dto.PostDetailDto;
import capstone.market.profile_dto.SearchFilterDto;
import capstone.market.repository.PostDataJpaRepository;
import capstone.market.repository.PostRepository;


import capstone.market.repository.PostRepositoryCustomImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostDataJpaRepository postDataJpaRepository;
    public Post findPostByPostId(Long post_id) {
        return postRepository.findOne(post_id);
    }

    public void savePost(Post post) {
        postRepository.savePost(post);
    }

    public List<Post> findPostByUserId(String user_id) {
        return postRepository.findByUserId(user_id);
    }
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> findBoughtListByUserId(Long user_id) {
        return postRepository.findBoughtListByUserId(user_id);
    }


    //@@@@@@@@@@@@@@@@@포스트 제목으로 검색하기 추가@@@@@@@@@@@@@@@@@@@
    public List<Post> findByTitleContaing(String keyword){

        return postRepository.SearchByTitle(keyword);

    }
    //@@@@@@@@@@@@@@@@@포스트 제목으로 검색하기 추가@@@@@@@@@@@@@@@@@@@

    //@@@@@@@@@@@@@@@@@카테고리로 필터링@@@@@@@@@@@@@@@@@@@
    public List<Post> SearchByCategory(CategoryType categoryType){

        return postRepository.SearchByCategory(categoryType);

    }
    //@@@@@@@@@@@@@@@@@카테고리로 필터링@@@@@@@@@@@@@@@@@@@

    //필터링 추가 3/23 @@@@@@@@ final version
    public List<PostDetailDto> SearchFilter(SearchFilterDto searchFilterDto){

        return postDataJpaRepository.searchFilter(searchFilterDto);

    }

    public void increaseViewCount(Long postId, Long userId){
        Post findPost = postRepository.findOne(postId);
        if(findPost != null){
            if(!findPost.getViewedUserIds().contains(userId)){
                findPost.setViews(findPost.getViews()+1);
                findPost.getViewedUserIds().add(userId);
                postRepository.savePost(findPost);
            }
        }
    }


    //필터링 추가 3/23 @@@@@@@@ final version


    // 3월 18일 추가
    // 게시글 수정
    public void update(PostForm request) {
        Post post = postRepository.findOne(request.getId());
        post.setPost_title(request.getTitle());
        post.setPost_text(request.getContent());
        post.setPrice(request.getPrice());
        post.getDepartment().setDepartmentType(request.getDepartmentType());
        post.setLocation_text(request.getLocation_text());
        post.setLocationType(request.getLocationType());
//        postRepository.savePost(post);
    }
}
