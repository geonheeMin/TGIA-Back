package capstone.market.profile_dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileListDto {

    private String username;
    private TrackType firstTrack;
    private TrackType secondTrack;

    public ProfileListDto(Member member) {
        this.username = member.getUsername();
        this.firstTrack = member.getFirstTrack().getFirst_track();
        this.secondTrack = member.getSecondTrack().getSecond_track();
    }
}
