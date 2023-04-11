package capstone.market.service;

import capstone.market.domain.*;
import capstone.market.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {
    private final PostDataJpaRepository postDataJpaRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final MemberDataJpa memberDataJpa;

    public ChatMessage findChatMessageById(Long id) {
        return chatMessageRepository.findById(id).get();
    }

    public Post findPostByPostId(Long id) {
        return postDataJpaRepository.findById(id).get();
    }

    public Member findMemberByMemberId(Long id) {
        return memberDataJpa.findById(id).get();
    }

    public ChatRoom findChatRoomByChatRoomId(Long id) {
        return chatRoomRepository.findById(id).get();
    }

    public ChatRoom startChatRoomService(Post post, Member memberA, Member memberB) {
        List<ChatRoom> chatRooms = post.getChatRooms();
        for (ChatRoom chatRoom : chatRooms) {
            if (chatRoom.getMemberA() == memberB) {
                return chatRoom;
            }
        }
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setPost(post);
        chatRoom.setMemberA(memberA);
        chatRoom.setMemberB(memberB);
        chatRoomRepository.save(chatRoom);
        // 기존 채팅이 있는지 없는 지

        return chatRoom;
    }

//    public ChatRoom startChatRoomServiceV2(Post post, Member sender, Member receiver) {
////        List<REChatRoom> chatRooms = post.getChatRooms();
//        ChatRoom chatRoom = new ChatRoom();
//        chatRoom.setPost(post);
//        chatRoom.setSender(sender);
//        chatRoom.setReceiver(receiver);
//        chatRoomRepository.save(chatRoom);
//
//        return chatRoom;
//    }


    public ChatMessage startChatMessageService(ChatRoom chatRoom, Member member, String message) {
        ChatMessage chatMessage = new ChatMessage(chatRoom, member, message, LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        chatMessage.setMember(member);
        chatMessage.setChatRoom(chatRoom);
        chatMessage.setLooked(false);
        chatMessageRepository.save(chatMessage);

        Long member_id = member.getId();

        System.out.println("$$$$$$$$ memberId = " +  member_id);
        System.out.println("$$$$$$$$ chatRoom.getMemberA().getId() = " + chatRoom.getMemberA().getId());
        if(member.getId() == chatRoom.getMemberA().getId()) {
            chatRoom.updateMessageCountB();
            System.out.println("worked updateMessageCountB" + chatRoom.getCount_b());
        } else {
            chatRoom.updateMessageCountA();
            System.out.println("worked updateMessageCountA " + chatRoom.getCount_a());
        }
        chatRoomRepository.save(chatRoom);
        return chatMessage;
    }

    public List<ChatRoom> getChatRoomLists(Long id) {
        return chatRoomRepository.findByPostPostId(id);
    }

//    public List<ChatRoom> getChatRoomListsByMemberId(Long member_id) {
//        List<ChatRoom> chatRoomList = chatRoomRepository.findByMemberId(member_id);
//        List<ChatRoom> chatRooms = chatRoomRepository.findByMemberAId(member_id);
//        chatRoomList.addAll(chatRooms);
//        return chatRoomList;
//    }

    public Long getUnreadMessageCount(Long chatroom_id) {
        return chatMessageRepository.countByLookedAndChatRoomId(false, chatroom_id);
    }

    public List<ChatMessage> getChatLists(Long id, Long member_id) {
        List<ChatMessage> chatMessages = chatMessageRepository.findByChatRoomId(id);
        ChatRoom chatRoom = chatRoomRepository.findById(id).get();
        Long memberB_id = chatRoom.getMemberB().getId();
        for (ChatMessage chatMessage : chatMessages) {
            if (chatMessage.getMember().getId() != memberB_id) {

            } else {
                chatMessage.setLooked(true);
                chatRoom.setCount_a(0L);
//                chatRoom.setCount_b(0L);
            }
        }
        return chatMessages;
    }

    public String createHourMinuteString(ChatMessage chatMessage) {
        LocalDateTime dateTime = chatMessage.getTime();
        return dateTime.getHour() + "시 " + dateTime.getMinute() + "분";
    }
}
