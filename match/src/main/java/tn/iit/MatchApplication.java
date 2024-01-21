package tn.iit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("tn.iit.feign")
@EnableDiscoveryClient
public class MatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatchApplication.class, args);
	}

}
