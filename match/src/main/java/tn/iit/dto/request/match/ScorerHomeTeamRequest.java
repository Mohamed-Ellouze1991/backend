package tn.iit.dto.request.match;

import lombok.*;
import tn.iit.entity.Match;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ScorerHomeTeamRequest {
    private int minute;
    private int homeTeam;
    private int scorer;
    private Match match;
}
