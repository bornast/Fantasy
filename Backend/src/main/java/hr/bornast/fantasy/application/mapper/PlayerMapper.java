package hr.bornast.fantasy.application.mapper;

import hr.bornast.fantasy.application.command.player.CreatePlayerCommand;
import hr.bornast.fantasy.application.command.player.UpdatePlayerCommand;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.player.PlayerDto;
import hr.bornast.fantasy.domain.model.Player;

public interface PlayerMapper {
    PlayerDto map(Player player);
    void map(UpdatePlayerCommand command, Player player);
    Player map(CreatePlayerCommand command);
    RecordNameDto mapRecordName(Player player);
}
