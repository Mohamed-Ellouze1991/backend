package tn.iit.dto.mapper;

import org.springframework.stereotype.Component;
import tn.iit.dto.request.PlayerRequest;
import tn.iit.dto.request.PlayerRequestPOST;
import tn.iit.dto.response.PlayerResponse;
import tn.iit.entity.Player;

@Component
public class PlayerMapper {

    public Player fromDTO(PlayerRequest playerRequest){
        return Player.builder()
                .idPlayer(playerRequest.getIdPlayer())
                .firstName(playerRequest.getFirstName())
                .lastName(playerRequest.getLastName())
                .age(playerRequest.getAge())
                .nationality(playerRequest.getNationality())
                .number(playerRequest.getNumber())
                .position(playerRequest.getPosition())
                .numberOfGoals(playerRequest.getNumberOfGoals())
                .state(playerRequest.getState())
                .team(playerRequest.getTeam())
                .build();
    }
    public Player fromDTOPost(PlayerRequestPOST playerRequest){
        return Player.builder()
                .idPlayer(playerRequest.getIdPlayer())
                .firstName(playerRequest.getFirstName())
                .lastName(playerRequest.getLastName())
                .age(playerRequest.getAge())
                .nationality(playerRequest.getNationality())
                .number(playerRequest.getNumber())
                .position(playerRequest.getPosition())
                .numberOfGoals(playerRequest.getNumberOfGoals())
                .state(playerRequest.getState())
                .build();
    }
    public PlayerResponse toDTO(Player player){
        return PlayerResponse.builder()
                .idPlayer(player.getIdPlayer())
                .age(player.getAge())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .nationality(player.getNationality())
                .number(player.getNumber())
                .numberOfGoals(player.getNumberOfGoals())
                .position(player.getPosition())
                .state(player.getState())
                .team(player.getTeam())
                .build();
    }
}
