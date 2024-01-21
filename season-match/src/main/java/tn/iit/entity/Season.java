package tn.iit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "t_season")
public class Season implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Include
    @Id
    private String seasonId;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "state")
    private String state;

}
