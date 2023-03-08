package capstone.market.controller;

import capstone.market.domain.Post;
import capstone.market.domain.Purchased;
import capstone.market.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final PostService postService;
    @GetMapping("/post/buy_list")
    public List<Purchased> getBuyerList() {
        return postService.findByadfasf(1L);
    }
}
