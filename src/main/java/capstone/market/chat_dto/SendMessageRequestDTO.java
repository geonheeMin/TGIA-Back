package capstone.market.chat_dto;

import lombok.Data;

@Data
public class SendMessageRequestDTO {
    private String chatroom_id;
    private String sender_id;
    private String message;

    public SendMessageRequestDTO() {
    }

    public SendMessageRequestDTO(String chatroom_id, String sender_id, String message) {
        this.chatroom_id = chatroom_id;
        this.sender_id = sender_id;
        this.message = message;
    }
}
