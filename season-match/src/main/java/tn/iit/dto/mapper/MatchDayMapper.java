package tn.iit.dto.mapper;

import tn.iit.dto.request.MatchDayRequest;
import tn.iit.dto.response.MatchDayResponse;
import tn.iit.entity.MatchDay;
import org.springframework.stereotype.Component;

@Component
public class MatchDayMapper {
        public MatchDay fromDTO(MatchDayRequest matchDayDTO) {
            return MatchDay.builder()

                    .startDate(matchDayDTO.getStartDate())
                    .endDate(matchDayDTO.getEndDate())
                    .name(matchDayDTO.getName())
                    .season(matchDayDTO.getSeason())
                    .build();
        }

        public MatchDayResponse toDTO(MatchDay matchDay) {
            return MatchDayResponse
                    .builder()
                    .idMatchDay(matchDay.getIdMatchDay())
                    .startDate(matchDay.getStartDate())
                    .endDate(matchDay.getEndDate())
                    .name(matchDay.getName())
                    .season(matchDay.getSeason())
                    .build();
        }
}
