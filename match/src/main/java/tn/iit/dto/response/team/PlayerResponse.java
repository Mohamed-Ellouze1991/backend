package tn.iit.dto.response.team;

import lombok.*;

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
    private String  state;
    private int age;
    private int number;
    private int numberOfGoals;
    private int teamId;
}
