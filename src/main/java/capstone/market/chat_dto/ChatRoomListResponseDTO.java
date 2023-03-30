package capstone.market.chat_dto;

import capstone.market.domain.ChatRoom;
import lombok.Data;

@Data
public class ChatRoomListResponseDTO {
    Long chatroom_id;
    Long count;
    String final_message;

    public ChatRoomListResponseDTO(ChatRoom chatRoom, String message) {
        this.chatroom_id = chatRoom.getId();
        this.count = chatRoom.getCount();
        this.final_message = message;
    }
}
