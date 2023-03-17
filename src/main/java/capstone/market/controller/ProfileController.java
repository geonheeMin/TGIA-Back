package capstone.market.controller;

import capstone.market.domain.*;
import capstone.market.profile_dto.ProfileImageChangeDTO;
import capstone.market.profile_dto.ProfileListDto;
import capstone.market.profile_dto.TrackUpdateDto;
import capstone.market.service.MemberService;
import capstone.market.service.PostService;
import capstone.market.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final MemberService memberService;
    private final PostService postService;
    private final TrackService trackService;
    @GetMapping("/post/buy_list")
    public List<Purchased> getBuyerList() {
        return postService.findByadfasf(1L);
    }

    @GetMapping("/profile")
    public ProfileListDto findMyProfileList(@RequestParam Long userId){
        Member findmember = memberService.findOne(userId);

        ProfileListDto profileListDto = new ProfileListDto(findmember);
        return profileListDto;
    }

    // 3월 17일
    // 프로필 이미지 변경
    @PostMapping("/profile/image_change")
    public void setProfileImage(@RequestBody ProfileImageChangeDTO profileImageChangeDTO) {
        memberService.setMemberImage(profileImageChangeDTO.getMember_id(), profileImageChangeDTO.getImage_filename());
    }

    //트랙 설정 및 업데이트
    @PostMapping("/profile/list/")
    public ProfileListDto UpdateTrack(@RequestBody TrackUpdateDto trackUpdateDto) {
        //1. 멤버를 찾는다
        Member user = memberService.findOne(trackUpdateDto.getUserId());
        System.out.println("trackUpdateDto = " + trackUpdateDto);

        //2. 트랙넘버에 따라 1트랙 2트랙으로 경우를 나눈다.
        if(trackUpdateDto.getTrackNumber() == 1){
        FirstTrack firstTrack = trackService.findFirstTrackByid(trackUpdateDto.getTrackId());
        trackService.saveFirstTrack(firstTrack,TrackType.valueOf(trackUpdateDto.getTrackname()));
        user.setFirstTrack(firstTrack);
    }
        if(trackUpdateDto.getTrackNumber() == 2){
            SecondTrack secondTrack = trackService.findSecondTrackByid(trackUpdateDto.getTrackId());
            trackService.saveSecondTrack(secondTrack,TrackType.valueOf(trackUpdateDto.getTrackname()));
            user.setSecondTrack(secondTrack);
        }

        return new ProfileListDto(user);
//        return "redirect:/profile";

       // 숫자로 1트랙 2트랙 판단한다. 0/1

        //1트랙인지, 멤버 아이디 , 트랙 아이디 필요함
    }







}
