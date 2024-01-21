package tn.iit.dto.request.match;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class MatchRequest {
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
