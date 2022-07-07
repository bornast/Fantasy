package hr.bornast.fantasy.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class Team {
    private int id;
    private String name;
    private Date dateOfEstablishment;
    private President president;
    private Coach coach;
    private Stadium stadium;
}
