package hr.bornast.fantasy.application.command.team;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SaveTeamCommand {
    @NotEmpty
    private String name;
    @NotNull
    private Date dateOfEstablishment;
    @NotNull
    private int presidentId;
    @NotNull
    private int coachId;
    @NotNull
    private int stadiumId;
}
