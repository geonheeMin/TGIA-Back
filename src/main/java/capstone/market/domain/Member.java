package capstone.market.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String user_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id")
    private Track track;
    private String username;

    public Member() {
    }

    public Member(String user_id) {
        this.user_id = user_id;
    }


}