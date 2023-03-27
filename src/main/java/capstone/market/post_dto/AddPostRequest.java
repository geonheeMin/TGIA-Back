package capstone.market.post_dto;

import capstone.market.domain.CategoryType;
import lombok.Data;

@Data
public class AddPostRequest {
    private String title;
    private Long user_id;
    private CategoryType category;
    private String content;
    //        private String time;
    private Integer price;
//        private String image_file_name;
}