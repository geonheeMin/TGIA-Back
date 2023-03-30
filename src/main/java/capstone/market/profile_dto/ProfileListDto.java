package capstone.market.profile_dto;

import capstone.market.domain.Member;
import capstone.market.domain.TrackType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileListDto {

    private String username;
    private TrackType firstTrack;
    private TrackType secondTrack;
    private String imageFileName;

    private Long AtrackId;
    private Long BtrackId;

    public ProfileListDto(Member member) {
        this.username = member.getUsername();
        this.firstTrack = member.getFirstTrack().getFirst_track();
        this.secondTrack = member.getSecondTrack().getSecond_track();
        this.AtrackId = member.getFirstTrack().getTrack_id();
        this.BtrackId = member.getSecondTrack().getTrack_id();
        this.imageFileName = member.getImage().getImageFilename();
    }
}
