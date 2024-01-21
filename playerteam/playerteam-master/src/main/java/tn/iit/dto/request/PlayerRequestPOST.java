package tn.iit.dto.request;


import lombok.*;
import tn.iit.entity.Team;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerRequestPOST {
    private int idPlayer;
    private String  firstName;
    private String  lastName;
    private String  nationality;
    private String  position;
    private int age;
    private int number;
    private int numberOfGoals;
    private String  state;
    private String team;
}
