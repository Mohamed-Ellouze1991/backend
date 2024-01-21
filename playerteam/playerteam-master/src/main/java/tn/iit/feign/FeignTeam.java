package tn.iit.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "PlayerTeam", path="/team ")
public interface FeignTeam {
}
