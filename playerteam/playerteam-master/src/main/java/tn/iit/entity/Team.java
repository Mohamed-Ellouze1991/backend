package tn.iit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder

@Entity
@Table(name = "t_team")
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTeam;
    @Column(name = "team_name")
    private String  teamName;
    @Column(name = "team_abbreviation")
    private String  teamAbbreviation;
    @Column(name = "team_coach")
    private String  teamCoach;
    @Column(name = "team_city")
    private String  teamCity;
    @Column(name = "team_points")
    private int teamPoints;
    @Column(name = "number_of_player")
    private int numberOfPlayer;

}
