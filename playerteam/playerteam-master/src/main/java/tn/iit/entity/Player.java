package tn.iit.entity;


import jakarta.persistence.*;
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
@Table(name = "t_player")
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPlayer;
    @Column(name = "first_name")
    private String  firstName;
    @Column(name = "last_name")
    private String  lastName;
    @Column(name = "nationality")
    private String  nationality;
    @Column(name = "position")
    private String  position;
    @Column(name = "age")
    private int age;
    @Column(name = "number")
    private int number;
    @Column(name = "goals_number")
    private int numberOfGoals;
    @Column(name = "state")
    private String state;
    @ManyToOne()
    @JoinColumn(name = "id_Team")
    private Team team;
}
