package tn.iit.dto.response;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class SeasonResponse {
    private String seasonId;
    private Date startDate;
    private Date endDate;
    private String state;
}
