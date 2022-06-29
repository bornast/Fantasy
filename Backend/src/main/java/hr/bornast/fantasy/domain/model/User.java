package hr.bornast.fantasy.domain.model;

import java.util.Set;

import hr.bornast.fantasy.infrastructure.persistence.entity.RoleEntity;
import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Set<RoleEntity> roles;
}
