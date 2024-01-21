package tn.iit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.iit.entity.Team;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team , Integer> {
    Optional<Team> findTeamByTeamName(String name);
}
