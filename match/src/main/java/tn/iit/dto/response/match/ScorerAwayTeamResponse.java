package tn.iit.dto.response.match;

import lombok.*;
import tn.iit.entity.Match;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ScorerAwayTeamResponse {
    private Integer detailsMatchId;
    private int minute;
    private int awayTeam;
    private int scorer;
    private Match match;
}
