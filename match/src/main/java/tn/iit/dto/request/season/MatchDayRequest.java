package tn.iit.dto.request.season;


import lombok.*;


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
    private String seasonId;
}
