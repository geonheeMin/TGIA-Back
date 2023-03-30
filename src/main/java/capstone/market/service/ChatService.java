package capstone.market.service;

import capstone.market.domain.ChatMessage;
import capstone.market.domain.ChatRoom;
import capstone.market.domain.Member;
import capstone.market.domain.Post;
import capstone.market.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

    public ChatRoom findChatRoomByChatRoomId(Long id) {
        return chatRoomRepository.findById(id).get();
    }

    public ChatRoom startChatRoomService(Post post, Member member) {
        List<ChatRoom> chatRooms = post.getChatRooms();
        for (ChatRoom chatRoom : chatRooms) {
            if (chatRoom.getMember() == member) {
                return chatRoom;
            }
        }
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setPost(post);
        chatRoom.setMember(member);
        chatRoomRepository.save(chatRoom);
        // 기존 채팅이 있는지 없는 지

        return chatRoom;
    }

    public ChatMessage startChatMessageService(ChatRoom chatRoom, Member member, String message) {
        ChatMessage chatMessage = new ChatMessage(chatRoom, member, message, LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        chatMessage.setMember(member);
        chatMessage.setChatRoom(chatRoom);
        chatMessage.setLooked(false);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatRoom> getChatRoomLists(Long id) {
        return chatRoomRepository.findByPostPostId(id);
    }

    public List<ChatMessage> getChatLists(Long id, Long member_id) {
        List<ChatMessage> chatMessages = chatMessageRepository.findByChatRoomId(id);
        for (ChatMessage chatMessage : chatMessages) {
            if (chatMessage.getMember().getId() != member_id)
                chatMessage.setLooked(true);
        }
        return chatMessages;
    }

    public String createHourMinuteString(ChatMessage chatMessage) {
        LocalDateTime dateTime = chatMessage.getTime();
        return dateTime.getHour() + "시 " + dateTime.getMinute() + "분";
    }
}
