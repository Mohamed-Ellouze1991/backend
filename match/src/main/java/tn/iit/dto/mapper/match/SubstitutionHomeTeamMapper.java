package tn.iit.dto.mapper.match;

import org.springframework.stereotype.Component;
import tn.iit.dto.request.match.SubstitutionHomeTeamRequest;
import tn.iit.dto.response.match.SubstitutionHomeTeamResponse;
import tn.iit.entity.DetailsMatch;

@Component
public class SubstitutionHomeTeamMapper {
    public DetailsMatch fromDTO(SubstitutionHomeTeamRequest substitutionHomeTeamRequest){
        return DetailsMatch.builder()
                .minute(substitutionHomeTeamRequest.getMinute())
                .homeTeam(substitutionHomeTeamRequest.getHomeTeam())
                .holderPlayerId(substitutionHomeTeamRequest.getHolderPlayerId())
                .substitutionPlayerId(substitutionHomeTeamRequest.getSubstitutionPlayerId())
                .match(substitutionHomeTeamRequest.getMatch())
                .build();
    }

    public SubstitutionHomeTeamResponse toDTO(DetailsMatch detailsMatch){
        return SubstitutionHomeTeamResponse.builder()
                .detailsMatchId(detailsMatch.getDetailsMatchId())
                .minute(detailsMatch.getMinute())
                .homeTeam(detailsMatch.getHomeTeam())
                .holderPlayerId(detailsMatch.getHolderPlayerId())
                .substitutionPlayerId(detailsMatch.getSubstitutionPlayerId())
                .match(detailsMatch.getMatch())
                .build();
    }
}
