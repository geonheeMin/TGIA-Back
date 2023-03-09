package capstone.market.controller;

import capstone.market.domain.ChatMessage;
import capstone.market.domain.ChatRoom;
import capstone.market.domain.Member;
import capstone.market.domain.Post;
import capstone.market.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    // 문의하기 버튼을 눌렀을 경우
    @PostMapping("/chat/start")
    public ChatRoom chatStart(Post post, Member member) {
        ChatRoom chatRoom = chatService.startChatRoomService(post, member);
        return chatRoom;
    }

    @PostMapping("/chat/send")
    public ChatMessage sendMessage(ChatRoom chatRoom, Member member, String message) {
        ChatMessage chatMessage = chatService.startChatMessageService(chatRoom, member, message);
        return chatMessage;
    }
}
