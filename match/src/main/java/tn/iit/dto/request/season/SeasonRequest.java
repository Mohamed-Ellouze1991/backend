package tn.iit.dto.request.season;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SeasonRequest {
    private String seasonId;
    private Date startDate;
    private Date endDate;

}
