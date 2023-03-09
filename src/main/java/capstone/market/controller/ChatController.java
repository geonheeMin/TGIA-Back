package capstone.market.controller;

import capstone.market.domain.ChatRoom;
import capstone.market.domain.Member;
import capstone.market.domain.Post;
import capstone.market.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequiredArgsConstructor
//public class ChatController {
//    private final ChatService chatService;
//    @PostMapping("/chat_start")
//    public void chatStart(Post post, Member member, String message) {
//        ChatRoom chatRoom = chatService.startChatRoomService(post);
//        chatService.startChatMessageService(chatRoom, member, message);
//    }
//}
