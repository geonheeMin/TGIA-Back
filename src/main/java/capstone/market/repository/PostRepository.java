package capstone.market.repository;

import capstone.market.domain.*;
import capstone.market.domain.ChatRoom;
import capstone.market.domain.Post;
import capstone.market.domain.Purchased;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class PostRepository {
    private final EntityManager em;

    public void savePost(Post post) {
        em.persist(post);
    }

    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }

    public List<Post> findAll() {
        return em.createQuery("select p from Post p")
                .getResultList();
    }

    //@@@@@@@@@@@@@@@@@포스트 제목으로 검색하기 추가@@@@@@@@@@@@@@@@@@@.setParameter("keyword", keyword)
    public List<Post> SearchByTitle(String keyword) {

       // String jpql = "SELECT p FROM Post p WHERE p.post_title =:keyword";
        String jpql = "SELECT p FROM Post p WHERE p.post_title LIKE CONCAT('%', :keyword, '%')";

        return em.createQuery(jpql, Post.class)
                .setParameter("keyword",  keyword )
                .setMaxResults(10)
                .getResultList();
    }

    //@@@@@@@@@@@@@@@@@포스트 제목으로 검색하기 추가@@@@@@@@@@@@@@@@@@@

    //@@@@@@@@@@@@@@@@@카테고리로 포스트 필터링@@@@@@@@@@@@@@@@@@@
    public List<Post> SearchByCategory(CategoryType category) {

         String jpql = "SELECT p FROM Post p WHERE p.category.category_type =:category";


        return em.createQuery(jpql, Post.class)
                .setParameter("category",  category )
                .setMaxResults(10)
                .getResultList();
    }

    //@@@@@@@@@@@@@@@@@카테고리로 포스트 필터링@@@@@@@@@@@@@@@@@@@


    public List<Post> findByUserId(String user_id) {
        String jpql = "select p from Post p join p.who_posted m where m.user_id = :user_id";

        return em.createQuery(jpql, Post.class)
                .setParameter("user_id", user_id)
                .getResultList();
    }

    public List<ChatRoom> findByPostId(Long room_id) {
        String jpql = "select p from Post p join ChatRoom c where c.id = :room_id";

        return em.createQuery(jpql, ChatRoom.class)
                .setParameter("room_id", room_id)
                .getResultList();
    }

    // 구매 목록
    public List<Post> findBoughtListByUserId(Long user_id) {

        String jpql2 = "select p from Post p where p.purchased.member.id = :user_id and p.purchased is not null ";


        List<Post> list = em.createQuery(jpql2, Post.class)
                .setParameter("user_id", user_id)
                .getResultList();

        return list;
    }






}
