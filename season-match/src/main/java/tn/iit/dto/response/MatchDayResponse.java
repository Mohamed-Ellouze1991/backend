package tn.iit.dto.response;

import lombok.*;
import tn.iit.entity.Season;

import java.util.Date;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class MatchDayResponse {
    private Integer idMatchDay;
    private String name;
    private Date startDate;
    private Date endDate;
    private Season season;
}
