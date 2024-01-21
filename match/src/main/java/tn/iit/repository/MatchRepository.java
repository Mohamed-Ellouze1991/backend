package tn.iit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.iit.entity.Match;

import java.util.Collection;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Integer> {
    List<Match> findByMatchDayId(Integer matchDayId);
    Match findByMatchDayIdAndAwayTeamIdAndHomeTeamId(Integer matchDayId,int awayTeamId, int homeTeamId);
    Match findByAwayTeamIdAndHomeTeamId(int awayTeamId, int homeTeamId);
}
