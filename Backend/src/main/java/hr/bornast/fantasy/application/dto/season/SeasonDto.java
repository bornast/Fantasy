package hr.bornast.fantasy.application.dto.season;

import java.util.Date;

import lombok.Data;

@Data
public class SeasonDto {
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
}
