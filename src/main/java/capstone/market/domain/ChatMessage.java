package capstone.market.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter
public class ChatMessage {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Member member;

    public ChatMessage(String text) {
        this.text = text;
    }

    public ChatMessage(ChatRoom chatRoom, Member member, String text) {
        this.text = text;
        this.chatRoom = chatRoom;
        this.member = member;
    }
}
