package hr.bornast.fantasy.application.command.referee;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateRefereeCommand {
    @NotNull
    private String name;
    @NotNull
    private Date dateOfBirth;
}
