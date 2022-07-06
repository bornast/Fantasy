package hr.bornast.fantasy.application.command.coach;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateCoachCommand {
    @NotNull
    private String name;
    @NotNull
    private Date dateOfBirth;
}
