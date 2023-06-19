package capstone.market.bugigram;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private Integer userId;
    private String email;
    private String name;
    private String nickname;
    private String password;

    private String imageFilename;

    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.password = user.getPassword();
        this.imageFilename = user.getImageFilename();
    }
}
