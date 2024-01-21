package tn.iit.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.EqualsAndHashCode.Include;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder

@Entity
@Table(name = "t_matchday")
public class MatchDay implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMatchDay;
    @Column(name = "name")
    private String name;
    @Column(name = "startDate_match")
    private Date startDate;
    @Column(name = "endDate_match")
    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "seasonId")
    private Season season;

}
