package hr.bornast.fantasy.application.command.rate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateRateCommand {
    @NotNull
    @Min(1)
    @Max(10)
    private int rate;
}
