package capstone.market.post_dto;

import lombok.Data;

@Data
public class PostForm {
    private Long id;
//    게시글 수정시 게시물을 올린 사람의 정보는 수정할 일은 없을 것 같다.
//    private Long who_posted;
    private Integer price;
    private String post_title;
    private String post_text;
    private Long department_id;
    private Long image_id;
}
