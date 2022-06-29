package hr.bornast.fantasy.domain.model;

import java.util.Set;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Set<Role> roles;
    public void addRole(Role role) {
        roles.add(role);
    }
}
