package tn.iit.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder

@Entity
@Table(name = "t_match")
public class Match implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matchId;
    @Column(name = "date_match")
    private Date date;
    @Column(name = "number_spectator")
    private int numberSpectator;
    @Column(name = "referee")
    private String referee;
    @Column(name = "stadium")
    private String stadium;
    @Column(name = "matchDayId")
    private Integer matchDayId;
    @Column(name = "homeTeamId")
    private int homeTeamId;
    @Column(name = "awayTeamId")
    private int awayTeamId;
    @Column(name = "result")
    private String result;
    @Column(name = "state")
    private String state;


}
