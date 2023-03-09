package capstone.market.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ChatMessage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;

//    @JoinColumn(name = "sender_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Member sender;
//    @Column(name = "timestamp")
//    private LocalDateTime timestamp;
}
