package capstone.market.profile_dto;

import capstone.market.domain.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PostDetailDto {

    private Long post_id;
    private String title;
    private Long user_id;


    private CategoryType category;
    private DepartmentType department;
    private String text;
    private Integer price;

    private Integer views;
    private Integer likes;
    private LocationType locationType;
    private String location_text;
    private List<String> images = new ArrayList<>();



    public PostDetailDto(Post post) {
        this.post_id = post.getPostId();
        this.title = post.getPost_title();
        this.user_id = post.getWho_posted().getId();
        this.category = post.getCategory().getCategory_type();
        this.text = post.getPost_text();
        this.price = post.getPrice();
        this.department = post.getDepartment().getDepartmentType();
        this.views = post.getViews();
        this.likes = post.getLikes();
        this.locationType = post.getLocationType();
        this.location_text = post.getLocation_text();

        if (post.getImages().isEmpty()) {
            images.add("hello world");
        } else {
            for (Image image : post.getImages()) {
                images.add(image.getImageFilename());
            }
        }
    }
}
