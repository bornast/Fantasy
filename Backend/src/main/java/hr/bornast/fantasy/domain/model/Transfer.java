package hr.bornast.fantasy.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class Transfer {
    private int id;
    private Player player;
    private Team fromTeam;
    private Team toTeam;
    private Date transferDate;
}
