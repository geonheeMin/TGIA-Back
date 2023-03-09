package capstone.market.initData;

import capstone.market.domain.Member;
import capstone.market.domain.Post;
import capstone.market.domain.Purchased;
import capstone.market.repository.MemberRepository;
import capstone.market.repository.PostRepository;
import capstone.market.repository.PurchasedRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Slf4j
public class DataLoader {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PurchasedRepository purchasedRepository;

    @PostConstruct
    public void init() {
        // 초기화 작업 수행
        Member member1 = new Member("hello");
        memberRepository.save(member1);

        Post post = new Post();
        post.setWho_posted(member1);
        postRepository.savePost(post);

        // error
        System.out.println("post.getWho_posted() = " + post.getWho_posted());

        Purchased purchased = new Purchased();
        purchased.setMember(member1);
        purchased.setPost(post);
        purchasedRepository.save(purchased);

        List<Purchased> postList = postRepository.findBoughtListByUserId(member1.getId());
        for (Purchased post1 : postList) {
            System.out.println("post1$$$$$$$$$ = " + post1.getPost());
        }
    }
}