package capstone.market.service;

import capstone.market.domain.ChatMessage;
import capstone.market.domain.ChatRoom;
import capstone.market.domain.Member;
import capstone.market.domain.Post;
import capstone.market.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {
    private final PostDataJpaRepository postDataJpaRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final MemberDataJpa memberDataJpa;

    public Post findPostByPostId(Long id) {
        return postDataJpaRepository.findById(id).get();
    }

    public Member findMemberByMemberId(Long id) {
        return memberDataJpa.findById(id).get();
    }

    public ChatRoom startChatRoomService(Post post, Member member) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setPost(post);
        chatRoom.setMember(member);
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }

    public ChatMessage startChatMessageService(ChatRoom chatRoom, Member member, String message) {
        ChatMessage chatMessage = new ChatMessage(chatRoom, member, message, LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        chatMessage.setMember(member);
        chatMessage.setChatRoom(chatRoom);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatRoom> getChatRoomLists(Long id) {
        return chatRoomRepository.findByPostPostId(id);
    }

    public List<ChatMessage> getChatLists(Long id) {
        return chatMessageRepository.findByChatRoomId(id);
    }
}
