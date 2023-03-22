package capstone.market.repository;

import capstone.market.domain.ChatRoom;
import capstone.market.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface PostDataJpaRepository extends JpaRepository<Post, Long> {
    List<ChatRoom> findChatRoomsByPostId(Long postId);





}
