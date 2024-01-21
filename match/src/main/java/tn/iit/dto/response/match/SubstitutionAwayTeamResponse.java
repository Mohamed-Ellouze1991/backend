package tn.iit.dto.response.match;

import lombok.*;
import tn.iit.entity.Match;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class SubstitutionAwayTeamResponse {
    private Integer detailsMatchId;
    private int minute;
    private int awayTeam;
    private int holderPlayerId;
    private int substitutionPlayerId;
    private Match match;
}
