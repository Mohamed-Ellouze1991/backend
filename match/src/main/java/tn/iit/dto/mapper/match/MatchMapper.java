package tn.iit.dto.mapper.match;


import org.springframework.stereotype.Component;
import tn.iit.dto.request.match.MatchRequest;
import tn.iit.dto.response.match.MatchResponse;
import tn.iit.entity.Match;

@Component
public class MatchMapper {

    public Match fromDTO(MatchRequest matchRequest){
        return Match.builder()
                .matchDayId(matchRequest.getMatchDayId())
                .date(matchRequest.getDate())
                .stadium(matchRequest.getStadium())
                .referee(matchRequest.getReferee())
                .state(matchRequest.getState())
                .result(matchRequest.getResult())
                .numberSpectator(matchRequest.getNumberSpectator())
                .homeTeamId(matchRequest.getHomeTeamId())
                .awayTeamId(matchRequest.getAwayTeamId())
                .build();
    }

    public MatchResponse toDTO(Match match){
        return MatchResponse.builder()
                .matchId(match.getMatchId())
                .awayTeamId(match.getAwayTeamId())
                .date(match.getDate())
                .homeTeamId(match.getHomeTeamId())
                .state(match.getState())
                .result(match.getResult())
                .matchDayId(match.getMatchDayId())
                .numberSpectator(match.getNumberSpectator())
                .referee(match.getReferee())
                .stadium(match.getStadium())
                .build();
    }
}
