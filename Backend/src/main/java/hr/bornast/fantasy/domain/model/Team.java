package hr.bornast.fantasy.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Team {
    private int id;
    private String name;
    private Date dateOfEstablishment;
    private President president;
    private Coach coach;
    private Stadium stadium;
    private Set<User> favoredUsers = new HashSet<>();

    public void addFavouredUser(User user) {
        favoredUsers.add(user);
    }
}
