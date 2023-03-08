package capstone.market.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// 구매 목록
@Entity
@Data
public class Purchased {
    @Id @GeneratedValue
    private Long id;
    private String productName;
    private Integer price;

    /**
     * 유저 한명당 구매 수 있는게 여러가지
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member buyer_id;

    /**
     *
     */
    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "member_id")
    private Post purchased_id;
}
