package tn.iit.dto.request;


import lombok.*;
import tn.iit.entity.Season;

import java.util.Date;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MatchDayRequest {

    private String name;
    private Date startDate;
    private Date endDate;
    private Season season;
}
