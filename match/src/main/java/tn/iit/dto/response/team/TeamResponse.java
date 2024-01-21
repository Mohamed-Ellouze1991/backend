package tn.iit.dto.response.team;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class TeamResponse {

    private int idTeam;
    private String  teamName;
    private String  teamAbbreviation;
    private String  teamCoach;
    private String  teamCity;
    private int teamPoints;
    private int numberOfPlayer;
}
