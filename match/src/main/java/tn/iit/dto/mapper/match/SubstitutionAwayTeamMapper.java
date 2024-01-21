package tn.iit.dto.mapper.match;

import org.springframework.stereotype.Component;
import tn.iit.dto.request.match.SubstitutionAwayTeamRequest;
import tn.iit.dto.response.match.SubstitutionAwayTeamResponse;
import tn.iit.entity.DetailsMatch;

@Component
public class SubstitutionAwayTeamMapper {
    public DetailsMatch fromDTO(SubstitutionAwayTeamRequest substitutionAwayTeamRequest){
        return DetailsMatch.builder()
                .minute(substitutionAwayTeamRequest.getMinute())
                .awayTeam(substitutionAwayTeamRequest.getAwayTeam())
                .holderPlayerId(substitutionAwayTeamRequest.getHolderPlayerId())
                .substitutionPlayerId(substitutionAwayTeamRequest.getSubstitutionPlayerId())
                .match(substitutionAwayTeamRequest.getMatch())
                .build();
    }

    public SubstitutionAwayTeamResponse toDTO(DetailsMatch detailsMatch){
        return SubstitutionAwayTeamResponse.builder()
                .detailsMatchId(detailsMatch.getDetailsMatchId())
                .minute(detailsMatch.getMinute())
                .awayTeam(detailsMatch.getAwayTeam())
                .holderPlayerId(detailsMatch.getHolderPlayerId())
                .substitutionPlayerId(detailsMatch.getSubstitutionPlayerId())
                .match(detailsMatch.getMatch())
                .build();
    }
}
