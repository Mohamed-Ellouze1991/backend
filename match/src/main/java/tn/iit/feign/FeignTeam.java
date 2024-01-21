package tn.iit.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tn.iit.core.ServerResponse;
import tn.iit.dto.response.team.PlayerResponse;
import tn.iit.dto.response.team.TeamResponse;

@FeignClient(value = "player-service")
public interface FeignTeam {
    @GetMapping("playerteam/team/{id}")
    public ServerResponse<TeamResponse> getByIdTeam (@PathVariable int id) ;
    @GetMapping("playerteam/player/{id}")
    public ServerResponse<PlayerResponse> getByIdPlayer (@PathVariable int id) ;
    @PutMapping("playerteam/player/{id}")
    public ServerResponse<PlayerResponse> updatePlayer (@RequestBody PlayerResponse player, @PathVariable int id) ;
    @PutMapping("playerteam/team/{id}")
    public ServerResponse<TeamResponse> updateTeam (@RequestBody TeamResponse team, @PathVariable int id) ;
}

