package capstone.market.controller;

import capstone.market.chat_dto.ChatMessageResponseDTO;
import capstone.market.chat_dto.ChatRoomResponseDTO;
import capstone.market.chat_dto.ChatStartRequestDTO;
import capstone.market.chat_dto.SendMessageRequestDTO;
import capstone.market.domain.ChatMessage;
import capstone.market.domain.ChatRoom;
import capstone.market.domain.Member;
import capstone.market.domain.Post;
import capstone.market.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    // 게시물 페이지에서 채팅방 리스트를 조회할 경우
    // 게시물의 id(PK)를 넘겨줘야 됩니다.
    @GetMapping("/chat/get_chat_room_list")
    public List<ChatRoomResponseDTO> getChatRoomList(Long id) {
        List<ChatRoom> chatRoomLists = chatService.getChatRoomLists(id);
        List<ChatRoomResponseDTO> chatRoomResponseDTOS = new ArrayList<>();
        for (ChatRoom chatRoom : chatRoomLists) {
            ChatRoomResponseDTO roomResponseDTO = new ChatRoomResponseDTO(chatRoom);
            chatRoomResponseDTOS.add(roomResponseDTO);
        }

        return chatRoomResponseDTOS;
    }

    // 채팅방에 저장된 채팅 메시지 내역을 가져옵니다.
    // 채팅방(ChatRoom) id(PK)를 넘겨줘야 됩니다.
    @GetMapping("/chat/get_chat_message_list")
    public List<ChatMessageResponseDTO> getChatMessageList(Long id) {
        List<ChatMessage> chatLists = chatService.getChatLists(id);
        List<ChatMessageResponseDTO> chatMessageResponseDTOS = new ArrayList<>();

        for (ChatMessage chatMessage : chatLists) {
            chatMessageResponseDTOS.add(new ChatMessageResponseDTO(chatMessage));
        }

        return chatMessageResponseDTOS;
    }

    // 문의하기 버튼을 눌렀을 경우 -> 채팅방 참가자 정보를 리턴합니다.
    /* 리턴 타입
        {
        "member_a": "더미부기",
        "member_b": "건희"
        }
     */
    @PostMapping("/chat/start")
    public ChatRoomResponseDTO chatStart(@RequestBody ChatStartRequestDTO chatStartRequestDTO) {
        System.out.println("chatStartRequestDTO.getPost_id() = " + chatStartRequestDTO.getPost_id());

        Long post_id = Long.valueOf(chatStartRequestDTO.getPost_id());
        Long member_id = Long.valueOf(chatStartRequestDTO.getMember_id());

        Post post = chatService.findPostByPostId(post_id);
        Member member = chatService.findMemberByMemberId(member_id);

        ChatRoom chatRoom = chatService.startChatRoomService(post, member);

//        List<ChatMessage> chatLists = chatService.getChatLists(chatRoom.getId());
//        List<ChatMessageResponseDTO> chatMessageResponseDTOS = new ArrayList<>();
//
//        if (chatLists.isEmpty()) {
//            ChatMessage chatMessage = new ChatMessage();
//            chatMessage.setMember(member);
//            chatMessage.setChatRoom(chatRoom);
//            chatMessage.setTime(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
//            chatMessageResponseDTOS.add(new ChatMessageResponseDTO(chatMessage));
//            return chatMessageResponseDTOS;
//        }
//
//        for (ChatMessage chatMessage : chatLists) {
//            chatMessageResponseDTOS.add(new ChatMessageResponseDTO(chatMessage));
//        }
//
//        return chatMessageResponseDTOS;

        return new ChatRoomResponseDTO(chatRoom);
    }

    // chat_room_id, member_id, message 필요
    // 메시지 전송 버튼을 눌렀을 경우
    @PostMapping("/chat/send")
    public ChatMessageResponseDTO sendMessage(@RequestBody SendMessageRequestDTO sendMessageRequestDTO) {

        Long chatroom_id = Long.valueOf(sendMessageRequestDTO.getChatroom_id());
        Long sender_id = Long.valueOf(sendMessageRequestDTO.getSender_id());
        String message = sendMessageRequestDTO.getMessage();

        ChatRoom chatRoom = chatService.findChatRoomByChatRoomId(chatroom_id);
        Member member = chatService.findMemberByMemberId(sender_id);

        ChatMessage chatMessage = chatService.startChatMessageService(chatRoom, member, message);
        // 방금 전송한 메시지를 반환합니다 -> 본인이 전송한 것을 화면에 뿌려주기 위해서 그런데 프론트 안에서도 해결 가능?

        return new ChatMessageResponseDTO(chatMessage);
    }

    @PostMapping("/chat/send_V2")
    public List<ChatMessageResponseDTO> sendMessageV2(@RequestBody SendMessageRequestDTO sendMessageRequestDTO) {

        Long chatroom_id = Long.valueOf(sendMessageRequestDTO.getChatroom_id());
        Long sender_id = Long.valueOf(sendMessageRequestDTO.getSender_id());
        String message = sendMessageRequestDTO.getMessage();

        ChatRoom chatRoom = chatService.findChatRoomByChatRoomId(chatroom_id);
        Member member = chatService.findMemberByMemberId(sender_id);

        ChatMessage chatMessage = chatService.startChatMessageService(chatRoom, member, message);
        // 방금 전송한 메시지를 반환합니다 -> 본인이 전송한 것을 화면에 뿌려주기 위해서 그런데 프론트 안에서도 해결 가능?


        List<ChatMessage> chatLists = chatService.getChatLists(chatroom_id);
        List<ChatMessageResponseDTO> chatMessageResponseDTOS = new ArrayList<>();

        for (ChatMessage chatMessage2 : chatLists) {
            chatMessageResponseDTOS.add(new ChatMessageResponseDTO(chatMessage2));
        }

        return chatMessageResponseDTOS;


//        return new ChatMessageResponseDTO(chatMessage);
    }
}
