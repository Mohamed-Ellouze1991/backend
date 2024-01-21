package tn.iit.dto.request.match;

import lombok.*;
import tn.iit.entity.Match;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SubstitutionAwayTeamRequest {
    private int minute;
    private int awayTeam;
    private int holderPlayerId;
    private int substitutionPlayerId;
    private Match match;
}
