package capstone.market.service;

import capstone.market.repository.MemberRepository;
import capstone.market.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    // 회원 가입
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    public Member findMemberByUserId(String user_id) {
        return memberRepository.findMemberByUserId(user_id);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    // 찜 목록에 추가
//    public void addLiked(Long post_id, Long member_id) {
//        memberRepository.addLiked(postRepository.findOne(post_id), member_id);
//    }
}
