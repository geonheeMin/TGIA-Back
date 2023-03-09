package capstone.market.controller;

import capstone.market.domain.ChatMessage;
import capstone.market.domain.ChatRoom;
import capstone.market.domain.Member;
import capstone.market.domain.Post;
import capstone.market.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    // 게시물 페이지에서 채팅방 리스트를 조회할 경우
    // 게시물의 id(PK)를 넘겨줘야 됩니다.
    @GetMapping("/chat/get_chat_room_list")
    public List<ChatRoom> getChatRoomList(Long id) {
        List<ChatRoom> chatRoomLists = chatService.getChatRoomLists(id);
        return chatRoomLists;
    }

    // 채팅방에 저장된 채팅 메시지 내역을 가져옵니다.
    // 채팅방 id(PK)를 넘겨줘야 됩니다.
    @GetMapping("/chat/get_chat_message_list")
    public List<ChatMessage> getChatMessageList(Long id) {
        List<ChatMessage> chatLists = chatService.getChatLists(id);
        return chatLists;
    }

    // 문의하기 버튼을 눌렀을 경우
    @PostMapping("/chat/start")
    public ChatRoom chatStart(Post post, Member member) {
        ChatRoom chatRoom = chatService.startChatRoomService(post, member);
        return chatRoom;
    }

    // 메시지 전송 버튼을 눌렀을 경우
    @PostMapping("/chat/send")
    public ChatMessage sendMessage(ChatRoom chatRoom, Member member, String message) {
        ChatMessage chatMessage = chatService.startChatMessageService(chatRoom, member, message);
        // 방금 전송한 메시지를 반환합니다.
        return chatMessage;
    }
}
