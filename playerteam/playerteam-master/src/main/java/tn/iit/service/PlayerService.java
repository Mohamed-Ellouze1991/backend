package tn.iit.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tn.iit.core.ServerResponse;
import tn.iit.dto.mapper.PlayerMapper;
import tn.iit.dto.request.PlayerRequest;
import tn.iit.dto.request.PlayerRequestPOST;
import tn.iit.dto.response.PlayerResponse;
import tn.iit.dto.response.TeamResponse;
import tn.iit.entity.Player;
import tn.iit.entity.Team;
import tn.iit.repository.PlayerRepository;
import tn.iit.repository.TeamRepository;

@AllArgsConstructor
@Service
public class PlayerService {

	private PlayerRepository mPlayerRepository;
	private PlayerMapper mPlayerMapper;
	private TeamService mTeamService;
	private TeamRepository mTeamRepository;

	public ServerResponse<List<PlayerResponse>> getAll() {
		List<PlayerResponse> playerResponses = mPlayerRepository.findAll().stream().map(mPlayerMapper::toDTO)
				.collect(Collectors.toList());
		ServerResponse<List<PlayerResponse>> apiResponse = new ServerResponse<>();
		return apiResponse.success(HttpStatus.OK.value(), playerResponses);
	}

	public ServerResponse<List<PlayerResponse>> getAllByTeam(int teamId) {
		List<PlayerResponse> playerResponses = mPlayerRepository.findByTeam_IdTeam(teamId).stream()
				.map(mPlayerMapper::toDTO).collect(Collectors.toList());
		ServerResponse<List<PlayerResponse>> apiResponse = new ServerResponse<>();
		if (playerResponses.isEmpty()) {
			ServerResponse<TeamResponse> team = mTeamService.getById(teamId);
			if (team.getData() == null) {
				return apiResponse.echec(HttpStatus.NOT_FOUND.value(), "Team n'existe pas");
			} else {
				return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "Team n'a pas de compte");
			}
		} else {
			return apiResponse.success(HttpStatus.OK.value(), playerResponses);
		}

	}

	public ServerResponse<PlayerResponse> getById(int id) {
		Optional<Player> player = mPlayerRepository.findById(id);
		ServerResponse<PlayerResponse> apiResponse = new ServerResponse<>();
		if (player.isPresent()) {
			return apiResponse.success(HttpStatus.OK.value(), mPlayerMapper.toDTO(player.get()));
		} else {
			return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "IDENTIFIANT INVALIDE");
		}
	}

	public ServerResponse<PlayerResponse> create(PlayerRequestPOST playerRequest) {
		Player mPlayer = mPlayerMapper.fromDTOPost(playerRequest);
		Optional<Player> player = mPlayerRepository.findById(mPlayer.getIdPlayer());
		ServerResponse<PlayerResponse> apiResponse = new ServerResponse<>();
		if (player.isPresent()) {
			return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "exist");
		} else {

			//Optional<Team> team = mTeamRepository.findById(playerRequest.getTeam().getIdTeam());
		    {

				Player playerSaved = mPlayerRepository.save(mPlayer);
				return apiResponse.success(HttpStatus.CREATED.value(), mPlayerMapper.toDTO(playerSaved));
			}

		}
	}

	public ServerResponse<PlayerResponse> update(Player player, int id) {
		Optional<Player> playerToUpdate = mPlayerRepository.findById(id);
		ServerResponse<PlayerResponse> apiResponse = new ServerResponse<>();
		if (playerToUpdate.isPresent()) {
			Player getPlayerToUpdate = playerToUpdate.get();
			getPlayerToUpdate.setFirstName(player.getFirstName());
			getPlayerToUpdate.setLastName(player.getLastName());
			getPlayerToUpdate.setPosition(player.getPosition());
			getPlayerToUpdate.setAge(player.getAge());
			getPlayerToUpdate.setNumber(player.getNumber());
			getPlayerToUpdate.setNumberOfGoals(player.getNumberOfGoals());
			getPlayerToUpdate.setState(player.getState());
			Player playerUpdated = mPlayerRepository.save(getPlayerToUpdate);
			return apiResponse.success(HttpStatus.OK.value(), mPlayerMapper.toDTO(playerUpdated));
		} else {
			return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "IDENTIFIANT INVALIDE");
		}
	}

	public ServerResponse<PlayerResponse> delete(int id) {
		Optional<Player> playerToDelete = mPlayerRepository.findById(id);
		Optional<Team> team = mTeamRepository.findById(playerToDelete.get().getTeam().getIdTeam());
		ServerResponse<PlayerResponse> apiResponse = new ServerResponse<>();
		if (playerToDelete.isPresent()) {
			mPlayerRepository.deleteById(id);
			Team teamToUpdate = team.get();
			teamToUpdate.setNumberOfPlayer(teamToUpdate.getNumberOfPlayer()-1);
			mTeamRepository.save(teamToUpdate);

			return apiResponse.success(HttpStatus.OK.value(), null);
		} else {
			return apiResponse.echec(HttpStatus.BAD_REQUEST.value(), "IDENTIFIANT INVALIDE");
		}
	}
}
