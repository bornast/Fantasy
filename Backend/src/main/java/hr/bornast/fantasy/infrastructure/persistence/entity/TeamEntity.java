package hr.bornast.fantasy.infrastructure.persistence.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "teams")
@Data
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private Date dateOfEstablishment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", name = "president_id")
    private PresidentEntity president;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", name = "coach_id")
    private CoachEntity coach;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", name = "stadium_id")
    private StadiumEntity stadium;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "favourite_teams",
               joinColumns = { @JoinColumn(name = "team_id") },
               inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<UserEntity> favoredUsers;

}
