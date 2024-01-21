package tn.iit.dto.request.team;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamRequest {
    private int idTeam;
    private String  teamName;
    private String  teamAbbreviation;
    private String  teamCoach;
    private String  teamCity;
    private int teamPoints;
    private int numberOfPlayer;
}
