package hr.bornast.fantasy.application.dto.season;

import lombok.Data;

@Data
public class SeasonDto {
    private int id;
    private String name;
    private String startDate;
    private String endDate;
}
