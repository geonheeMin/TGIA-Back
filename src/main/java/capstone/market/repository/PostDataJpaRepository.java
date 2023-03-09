package capstone.market.repository;

import capstone.market.domain.ChatRoom;
import capstone.market.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostDataJpaRepository extends JpaRepository<Post, Long> {
    List<ChatRoom> findChatRoomsByPostId(Long postId);
}
