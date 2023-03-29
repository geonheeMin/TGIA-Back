package capstone.market.chat_dto;

import capstone.market.domain.ChatMessage;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessageResponseDTO {
    Long sender;
    Long message_id;
    String message;
    String time;
    Long chatroom_id;

    public ChatMessageResponseDTO(ChatMessage chatMessage) {
        this.sender = chatMessage.getMember().getId();
        this.message_id = chatMessage.getId();
        this.message = chatMessage.getMessage();
        this.chatroom_id = chatMessage.getChatRoom().getId();
        this.time = createHourMinuteString(chatMessage);
    }

    private String createHourMinuteString(ChatMessage chatMessage) {
        LocalDateTime dateTime = chatMessage.getTime();
        return dateTime.getHour() + "시 " + dateTime.getMinute() + "분";
    }
}
