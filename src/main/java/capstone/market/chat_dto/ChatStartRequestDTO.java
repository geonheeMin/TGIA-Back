package capstone.market.chat_dto;

import lombok.Data;

@Data
public class ChatStartRequestDTO {
    String post_id;
    String member_id;

    public ChatStartRequestDTO() {
    }

    public ChatStartRequestDTO(String post_id, String member_id) {
        this.post_id = post_id;
        this.member_id = member_id;
    }
}
