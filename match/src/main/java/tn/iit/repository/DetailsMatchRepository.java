package tn.iit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.iit.entity.DetailsMatch;

import java.util.List;

public interface DetailsMatchRepository extends JpaRepository<DetailsMatch, Integer> {
    List<DetailsMatch> findByMatch_MatchId(Integer matchId);
    List<DetailsMatch> findByMatch_MatchIdAndHomeTeamAndHolderPlayerIdAndSubstitutionPlayerId(Integer matchId,int homeTeam,int holderPlayerId,int substitutionPlayerId);
    List<DetailsMatch> findByMatch_MatchIdAndAwayTeamAndHolderPlayerIdAndSubstitutionPlayerId(Integer matchId,int awayTeam,int holderPlayerId,int substitutionPlayerId);
}
