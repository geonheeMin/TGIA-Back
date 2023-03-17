package capstone.market.service;

import capstone.market.domain.Member;
import capstone.market.repository.ImageRepository;
import capstone.market.repository.MemberDataJpa;
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
    private final ImageRepository imageRepository;

    // 회원 가입
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    public Member setMemberImage(Long id, String image_filename) {
        Member member = memberRepository.findOne(id);
        member.setImage(imageRepository.findByImageFilename(image_filename));
        return member;
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
