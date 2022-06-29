package hr.bornast.fantasy.infrastructure.persistence.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(columnDefinition = "serial")
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;

    // joinColumn are the columns which are gonna be generated in many to many table
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
               joinColumns = { @JoinColumn(name = "user_id") },
               inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<RoleEntity> roles;
}
