package tn.iit.dto.request.match;

import lombok.*;
import tn.iit.entity.Match;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SubstitutionHomeTeamRequest {
    private int minute;
    private int homeTeam;
    private int holderPlayerId;
    private int substitutionPlayerId;
    private Match match;
}
