package capstone.market.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ChatMessage {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;
    @OneToOne
    @JoinColumn(name = "sender_id")
    private Member member;
}
