package tn.iit.dto.response.match;

import lombok.*;
import tn.iit.entity.Match;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ScorerHomeTeamResponse {
    private Integer detailsMatchId;
    private int minute;
    private int homeTeam;
    private int scorer;
    private Match match;
}
