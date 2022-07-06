package hr.bornast.fantasy.application.command.president;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdatePresidentCommand {
    @NotNull
    private String name;
    @NotNull
    private Date dateOfBirth;
}
