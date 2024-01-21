package tn.iit.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder

@Entity
@Table(name = "t_detailsMatch")
public class DetailsMatch implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detailsMatchId;
    @Column(name = "minute")
    private int minute;
    @Column(name = "homeTeam")
    private int homeTeam;
    @Column(name = "awayTeam")
    private int awayTeam;
    @Column(name = "holderPlayerId")
    private int holderPlayerId;
    @Column(name = "substitutionPlayerId")
    private int substitutionPlayerId;
    @Column(name = "scorer")
    private int scorer;
    @ManyToOne
    @JoinColumn(name = "match_Id")
    private Match match;
}
