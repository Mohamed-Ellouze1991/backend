package tn.iit.dto.request.match;

import lombok.*;
import tn.iit.entity.Match;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ScorerAwayTeamRequest {
    private int minute;
    private int awayTeam;
    private int scorer;
    private Match match;
}
