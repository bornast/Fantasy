package hr.bornast.fantasy.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class President {
    private int id;
    private String name;
    private Date dateOfBirth;
}
