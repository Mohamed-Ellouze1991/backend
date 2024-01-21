package tn.iit.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.iit.core.ServerResponse;
import tn.iit.dto.response.season.MatchDayResponse;

@FeignClient(value = "season-service")
public interface FeignMatchDay {
    @GetMapping("seasonmatch/matchDay/{id}")
    public ServerResponse<MatchDayResponse> getById (@PathVariable Integer id) ;
}
