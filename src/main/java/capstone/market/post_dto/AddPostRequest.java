package capstone.market.post_dto;

import capstone.market.domain.CategoryType;
import capstone.market.domain.DepartmentType;
import capstone.market.domain.LocationType;
import lombok.Data;

import java.util.List;

@Data
public class AddPostRequest {
    private String title;
    private Long user_id;
    private CategoryType category;
    private String content;
    //        private String time;
    private Integer price;

    private DepartmentType department;
    private LocationType locationType;
    private String location_text;
//        private String image_file_name;
    private List<String> images;
}