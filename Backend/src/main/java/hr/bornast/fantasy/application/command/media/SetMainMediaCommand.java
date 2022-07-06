package hr.bornast.fantasy.application.command.media;

import lombok.Data;

@Data
public class SetMainMediaCommand {
    private int entityId;
    private int entityTypeId;
}
