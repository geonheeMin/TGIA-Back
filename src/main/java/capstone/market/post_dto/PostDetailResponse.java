package capstone.market.post_dto;

import capstone.market.domain.CategoryType;
import capstone.market.domain.Image;
import capstone.market.domain.LocationType;
import capstone.market.domain.Post;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostDetailResponse {
    private Long post_id;
    private String title;
    private String username;
    private CategoryType category;
    private String text;
    private Integer price;
    private List<String> images = new ArrayList<>();
    private Integer views;
    private Integer likes;
    private LocationType locationType;
    private String location_text;



    public PostDetailResponse(Post post) {
        this.post_id = post.getPostId();
        this.title = post.getPost_title();
        this.username = post.getWho_posted().getUsername();
//            this.category = post.getCategory().getCategory_type();
        this.text = post.getPost_text();
        this.price = post.getPrice();
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
