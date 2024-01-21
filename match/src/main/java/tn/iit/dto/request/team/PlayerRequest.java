package tn.iit.dto.request.team;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerRequest {
    private int idPlayer;
    private String  firstName;
    private String  lastName;
    private String  nationality;
    private String  position;
    private String  state;
    private int age;
    private int number;
    private int numberOfGoals;
    private int teamId;
}
