package tn.iit.dto.mapper;

import org.springframework.stereotype.Component;
import tn.iit.dto.request.TeamRequest;
import tn.iit.dto.response.TeamResponse;
import tn.iit.entity.Team;

@Component
public class TeamMapper {
    public Team fromDTO(TeamRequest teamRequest){
        return Team.builder()
                .idTeam(teamRequest.getIdTeam())
                .teamName(teamRequest.getTeamName())
                .teamCoach(teamRequest.getTeamCoach())
                .teamAbbreviation(teamRequest.getTeamAbbreviation())
                .teamCity(teamRequest.getTeamCity())
                .teamPoints(teamRequest.getTeamPoints())
                .numberOfPlayer(teamRequest.getNumberOfPlayer())
                .build();
    }

    public Team fromDTO(TeamResponse teamResponse){
        return Team.builder()
                .idTeam(teamResponse.getIdTeam())
                .teamName(teamResponse.getTeamName())
                .teamCoach(teamResponse.getTeamCoach())
                .teamAbbreviation(teamResponse.getTeamAbbreviation())
                .teamCity(teamResponse.getTeamCity())
                .teamPoints(teamResponse.getTeamPoints())
                .numberOfPlayer(teamResponse.getNumberOfPlayer())
                .build();
    }

    public TeamResponse toDTO(Team team){
        return TeamResponse.builder()
                .idTeam(team.getIdTeam())
                .teamName(team.getTeamName())
                .teamAbbreviation(team.getTeamAbbreviation())
                .teamCity(team.getTeamCity())
                .teamCoach(team.getTeamCoach())
                .teamPoints(team.getTeamPoints())
                .numberOfPlayer(team.getNumberOfPlayer())
                .build();
    }
}
