package capstone.market.service;

import capstone.market.domain.Post;
import capstone.market.domain.Purchased;
import capstone.market.repository.PostRepository;


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




}
