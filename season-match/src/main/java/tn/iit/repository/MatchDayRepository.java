package tn.iit.repository;

import tn.iit.entity.MatchDay;
import tn.iit.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchDayRepository extends JpaRepository<MatchDay, Integer> {
    List<MatchDay> findBySeason_SeasonId(String season);
}
