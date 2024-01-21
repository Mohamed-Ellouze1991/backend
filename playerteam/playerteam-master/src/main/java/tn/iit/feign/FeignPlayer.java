package tn.iit.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "PlayerTeam", path="/player ")
public interface FeignPlayer {
}
