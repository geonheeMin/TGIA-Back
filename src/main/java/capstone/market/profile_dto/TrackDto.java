package capstone.market.profile_dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrackDto {
    private TrackType TrackName;

    public TrackDto(TrackType trackName) {
        TrackName = trackName;
    }
}
