package capstone.market.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Image {
    @Id @GeneratedValue
    private Long id;
    private String imageFilename;

    public Image(String imageFilename) {
        this.imageFilename = imageFilename;
    }
}
