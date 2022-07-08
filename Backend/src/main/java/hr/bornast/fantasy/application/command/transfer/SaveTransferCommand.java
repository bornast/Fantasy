package hr.bornast.fantasy.application.command.transfer;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SaveTransferCommand {
    @NotNull
    private int playerId;

    private int fromTeamId;

    @NotNull
    private int toTeamId;

    @NotNull
    private Date transferDate;
}
