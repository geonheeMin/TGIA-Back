package capstone.market.post_dto;

import capstone.market.profile_dto.PostDetailDto;
import lombok.Data;

import java.util.List;

@Data
public class PostAndSellerPostsDTO {
    private PostDetailDto currentPost;
    private List<PostDetailDto> sellerPosts;

    public PostAndSellerPostsDTO(PostDetailDto currentPost, List<PostDetailDto> sellerPosts) {
        this.currentPost = currentPost;
        this.sellerPosts = sellerPosts;
    }
}
