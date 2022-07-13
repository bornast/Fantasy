package hr.bornast.fantasy.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Player {
    private int id;
    private String name;
    private Date dateOfBirth;
    private Position position;
    private Set<Match> matches = new HashSet<>();
}
