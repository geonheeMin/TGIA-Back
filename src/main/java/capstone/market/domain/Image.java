package capstone.market.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Image {
    @Id @GeneratedValue
    private Long id;
    private String imageFilename;
    @OneToOne(mappedBy = "postImg", fetch = FetchType.LAZY)
    private Post postImage;

    public Image(String imageFilename) {
        this.imageFilename = imageFilename;
    }
}
