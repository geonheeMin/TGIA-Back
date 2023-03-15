package capstone.market.chat_dto;

import capstone.market.domain.ChatRoom;
import lombok.Data;

@Data
public class ChatRoomResponseDTO {
    String member_a;
    String member_b;

    public ChatRoomResponseDTO(ChatRoom chatRoom) {
        this.member_a = chatRoom.getPost().getWho_posted().getUsername();
        this.member_b = chatRoom.getMember().getUsername();
    }
}
