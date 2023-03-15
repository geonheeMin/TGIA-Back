package capstone.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PostDataJpaRepository extends JpaRepository<Post, Long> {
    List<ChatRoom> findChatRoomsByPostId(Long postId);
}
