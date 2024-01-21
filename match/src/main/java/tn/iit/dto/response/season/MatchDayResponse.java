package tn.iit.dto.response.season;

import lombok.*;

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
    private String seasonId;
}
