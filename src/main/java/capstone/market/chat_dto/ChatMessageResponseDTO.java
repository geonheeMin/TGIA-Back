package capstone.market.chat_dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessageResponseDTO {
    String sender;
    String message;
    String time;

    public ChatMessageResponseDTO(ChatMessage chatMessage) {
        this.sender = chatMessage.getMember().getUsername();
        this.message = chatMessage.getMessage();
        this.time = createHourMinuteString(chatMessage);
    }

    private String createHourMinuteString(ChatMessage chatMessage) {
        LocalDateTime dateTime = chatMessage.getTime();
        return dateTime.getHour() + "시 " + dateTime.getMinute() + "분";
    }
}
