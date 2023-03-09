package capstone.market.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Post {
    @Id @GeneratedValue
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member who_posted;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    protected Category category;
    private Integer price;

    private String post_title;
    private String post_text;

    @OneToOne(mappedBy = "post")
    private Purchased purchased;

    @OneToMany(mappedBy = "post")
    private List<ChatRoom> chatRooms = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    private Image postImg;
    // time
    // place
    // depart
    // image
    public Post() {
    }

    public Post(String post_title, String post_text) {
        this.post_title = post_title;
        this.post_text = post_text;
    }

    public Post(Member who_posted, Category category, Integer price, String post_title, String post_text, Image postImg) {
        this.who_posted = who_posted;
        this.category = category;
        this.price = price;
        this.post_title = post_title;
        this.post_text = post_text;
        this.postImg = postImg;
    }
}
