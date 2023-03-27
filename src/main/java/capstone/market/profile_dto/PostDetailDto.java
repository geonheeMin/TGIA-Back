package capstone.market.profile_dto;

import capstone.market.domain.CategoryType;
import capstone.market.domain.DepartmentType;
import capstone.market.domain.LocationType;
import capstone.market.domain.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDetailDto {

    private Long post_id;
    private String title;
    private String user_id;
    private CategoryType category;
    private DepartmentType department;
    private String text;
    private Integer price;

    private Integer views;
    private Integer likes;
    private LocationType locationType;
    private String location_text;


    public PostDetailDto(Post post) {
        this.post_id = post.getPostId();
        this.title = post.getPost_title();
        this.user_id = post.getWho_posted().getUser_id();
        this.category = post.getCategory().getCategory_type();
        this.text = post.getPost_text();
        this.price = post.getPrice();
        this.department = post.getDepartment().getDepartmentType();
        this.views = post.getViews();
        this.likes = post.getLikes();
        this.locationType = post.getLocationType();
        this.location_text = post.getLocation_text();
    }
}
