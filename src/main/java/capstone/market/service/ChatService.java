package capstone.market.service;

import capstone.market.domain.ChatMessage;
import capstone.market.domain.ChatRoom;
import capstone.market.domain.Member;
import capstone.market.domain.Post;
import capstone.market.repository.ChatMessageRepository;
import capstone.market.repository.ChatRoomRepository;
import capstone.market.repository.PostDataJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    public ChatRoom startChatRoomService(Post post, Member member) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setPost(post);
        chatRoom.setMember(member);
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }

    public ChatMessage startChatMessageService(ChatRoom chatRoom, Member member, String message) {
        ChatMessage chatMessage = new ChatMessage(chatRoom, member, message);
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
