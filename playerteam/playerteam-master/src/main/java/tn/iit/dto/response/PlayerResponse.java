package tn.iit.dto.response;

import lombok.*;
import tn.iit.entity.Team;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class PlayerResponse {
    private int idPlayer;
    private String  firstName;
    private String  lastName;
    private String  nationality;
    private String  position;
    private int age;
    private int number;
    private int numberOfGoals;
    private String  state;
    private Team team;
}
