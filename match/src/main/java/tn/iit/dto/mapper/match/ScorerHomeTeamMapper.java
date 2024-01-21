package tn.iit.dto.mapper.match;

import org.springframework.stereotype.Component;
import tn.iit.dto.request.match.ScorerHomeTeamRequest;
import tn.iit.dto.response.match.ScorerHomeTeamResponse;
import tn.iit.entity.DetailsMatch;

@Component
public class ScorerHomeTeamMapper {
    public DetailsMatch fromDTO(ScorerHomeTeamRequest scorerHomeTeamRequest){
        return DetailsMatch.builder()
                .minute(scorerHomeTeamRequest.getMinute())
                .homeTeam(scorerHomeTeamRequest.getHomeTeam())
                .scorer(scorerHomeTeamRequest.getScorer())
                .match(scorerHomeTeamRequest.getMatch())
                .build();
    }

    public ScorerHomeTeamResponse toDTO(DetailsMatch detailsMatch){
        return ScorerHomeTeamResponse.builder()
                .detailsMatchId(detailsMatch.getDetailsMatchId())
                .minute(detailsMatch.getMinute())
                .homeTeam(detailsMatch.getHomeTeam())
                .scorer(detailsMatch.getScorer())
                .match(detailsMatch.getMatch())
                .build();
    }
}
