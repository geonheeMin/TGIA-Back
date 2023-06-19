package capstone.market.bugigram;


import capstone.market.domain.Image;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Integer userId;
    private String email;
    private String name;
    private String nickname;
    private String password;
    private String imageFilename;




}
