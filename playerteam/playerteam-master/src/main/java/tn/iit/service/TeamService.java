package tn.iit.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tn.iit.core.ServerResponse;
import tn.iit.dto.mapper.TeamMapper;
import tn.iit.dto.request.TeamRequest;
import tn.iit.dto.response.TeamResponse;
import tn.iit.entity.Player;
import tn.iit.entity.Team;
import tn.iit.repository.PlayerRepository;
import tn.iit.repository.TeamRepository;

@AllArgsConstructor
@Service
public class TeamService {

	private TeamRepository mTeamRepository;
	private TeamMapper mTeamMapper;
	private PlayerRepository mPlayerRepository;

	public ServerResponse<List<TeamResponse>> getAll() {
		List<TeamResponse> teamResponses = mTeamRepository.findAll().stream().map(mTeamMapper::toDTO)
				.collect(Collectors.toList());
		ServerResponse<List<TeamResponse>> apiResponse = new ServerResponse<>();
		return apiResponse.success(HttpStatus.OK.value(), teamResponses);
	}

	public ServerResponse<TeamResponse> getById(int idTeam) {
		Optional<Team> team = mTeamRepository.findById(idTeam);
		ServerResponse<TeamResponse> apiResponse = new ServerResponse<>();
		if (team.isPresent()) {
			return apiResponse.success(HttpStatus.OK.value(), mTeamMapper.toDTO(team.get()));
		} else {
			return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "IDENTIFIANT INVALIDE");
		}
	}
	public ServerResponse<TeamResponse> getTeamByName(String name) {
		Optional<Team> team = mTeamRepository.findTeamByTeamName(name);
		ServerResponse<TeamResponse> apiResponse = new ServerResponse<>();
		if (team.isPresent()) {
			return apiResponse.success(HttpStatus.OK.value(), mTeamMapper.toDTO(team.get()));
		} else {
			return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "NOM INVALIDE");
		}
	}

	public ServerResponse<TeamResponse> create(TeamRequest teamRequest) {
		Team mTeam = mTeamMapper.fromDTO(teamRequest);
		Optional<Team> team = mTeamRepository.findTeamByTeamName(teamRequest.getTeamName());
		ServerResponse<TeamResponse> apiResponse = new ServerResponse<>();
		if (team.isPresent()) {
			return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "exist");
		} else {
			Team teamSaved = mTeamRepository.save(mTeam);
			return apiResponse.success(HttpStatus.CREATED.value(), mTeamMapper.toDTO(teamSaved));
		}
	}

	public ServerResponse<TeamResponse> update(Team team, int id) {
		Optional<Team> teamToUpdate = mTeamRepository.findById(id);
		ServerResponse<TeamResponse> apiResponse = new ServerResponse<>();
		if (teamToUpdate.isPresent()) {
			Team getTeamToUpdate = teamToUpdate.get();
			getTeamToUpdate.setTeamName(team.getTeamName());
			getTeamToUpdate.setTeamAbbreviation(team.getTeamAbbreviation());
			getTeamToUpdate.setTeamCoach(team.getTeamCoach());
			getTeamToUpdate.setTeamPoints(team.getTeamPoints());
			Team teamUpdated = mTeamRepository.save(getTeamToUpdate);
			return apiResponse.success(HttpStatus.OK.value(), mTeamMapper.toDTO(teamUpdated));
		} else {
			return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "IDENTIFIANT INVALIDE");
		}
	}

	public ServerResponse<TeamResponse> delete(int id) {
		Optional<Team> teamToDelete = mTeamRepository.findById(id);
		ServerResponse<TeamResponse> apiResponse = new ServerResponse<>();
		if (teamToDelete.isPresent()) {
			for (Player player : mPlayerRepository.findByTeam_IdTeam(id)) {
				mPlayerRepository.deleteById(player.getIdPlayer());
			}

			mTeamRepository.deleteById(id);

			return apiResponse.success(HttpStatus.OK.value(), null);
		} else {
			return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "IDENTIFIANT INVALIDE");
		}

	}
}
