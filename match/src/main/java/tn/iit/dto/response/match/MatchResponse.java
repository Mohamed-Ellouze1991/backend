package tn.iit.dto.response.match;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class MatchResponse {

    private Integer matchId;
    private Date date;
    private int numberSpectator;
    private String referee;
    private String stadium;
    private String state;
    private String result;
    private Integer matchDayId;
    private int homeTeamId;
    private int awayTeamId;
}
