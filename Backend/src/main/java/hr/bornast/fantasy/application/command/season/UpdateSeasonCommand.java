package hr.bornast.fantasy.application.command.season;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateSeasonCommand {
    @NotNull
    private String name;
    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;
}
