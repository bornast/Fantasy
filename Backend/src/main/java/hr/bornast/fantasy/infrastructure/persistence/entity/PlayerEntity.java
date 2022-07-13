package hr.bornast.fantasy.infrastructure.persistence.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "players")
@Data
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private Date dateOfBirth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", name = "position_id")
    private PositionEntity position;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "player",
               cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Set<MatchPlayerEntity> matches = new HashSet<>();
}
