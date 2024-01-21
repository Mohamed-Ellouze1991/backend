package tn.iit.dto.mapper.match;

import org.springframework.stereotype.Component;
import tn.iit.dto.request.match.ScorerAwayTeamRequest;
import tn.iit.dto.response.match.ScorerAwayTeamResponse;
import tn.iit.entity.DetailsMatch;

@Component
public class ScorerAwayTeamMapper {

    public DetailsMatch fromDTO(ScorerAwayTeamRequest scorerAwayTeamRequest){
        return DetailsMatch.builder()
                .minute(scorerAwayTeamRequest.getMinute())
                .awayTeam(scorerAwayTeamRequest.getAwayTeam())
                .scorer(scorerAwayTeamRequest.getScorer())
                .match(scorerAwayTeamRequest.getMatch())
                .build();
    }

    public ScorerAwayTeamResponse toDTO(DetailsMatch detailsMatch){
        return ScorerAwayTeamResponse.builder()
                .detailsMatchId(detailsMatch.getDetailsMatchId())
                .minute(detailsMatch.getMinute())
                .awayTeam(detailsMatch.getAwayTeam())
                .scorer(detailsMatch.getScorer())
                .match(detailsMatch.getMatch())
                .build();
    }
}
