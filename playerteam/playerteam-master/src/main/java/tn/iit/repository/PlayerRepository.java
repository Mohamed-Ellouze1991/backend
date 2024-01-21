package tn.iit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.iit.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
	List<Player> findByTeam_IdTeam(int id);
}
