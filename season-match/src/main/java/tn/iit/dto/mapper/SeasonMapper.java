package tn.iit.dto.mapper;


import tn.iit.dto.request.SeasonRequest;
import tn.iit.dto.response.SeasonResponse;

import org.springframework.stereotype.Component;
import tn.iit.entity.Season;

@Component
public class SeasonMapper {
    public Season fromDTO(SeasonRequest seasonDTO) {
        return Season.builder()
                .seasonId(seasonDTO.getSeasonId())
                .startDate(seasonDTO.getStartDate())
                .endDate(seasonDTO.getEndDate())
                .state(seasonDTO.getState())
                .build();
    }

    public SeasonResponse toDTO(Season season) {
        return SeasonResponse
                .builder()
                .seasonId(season.getSeasonId())
                .startDate(season.getStartDate())
                .endDate(season.getEndDate())
                .state(season.getState())
                .build();
    }
}
