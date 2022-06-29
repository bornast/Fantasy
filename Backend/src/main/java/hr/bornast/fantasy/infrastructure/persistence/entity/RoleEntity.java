package hr.bornast.fantasy.infrastructure.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(columnDefinition = "serial")
    private int id;
    private String name;
}
