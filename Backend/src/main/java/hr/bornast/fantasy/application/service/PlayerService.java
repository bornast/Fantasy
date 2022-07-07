package hr.bornast.fantasy.application.service;

import java.util.List;

import hr.bornast.fantasy.application.command.player.CreatePlayerCommand;
import hr.bornast.fantasy.application.command.player.UpdatePlayerCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.player.PlayerDto;
import org.springframework.data.domain.Pageable;

public interface PlayerService {
    PagedListDto<PlayerDto> findAll(Pageable pageable, String name);
    List<RecordNameDto> findAllRecordNames();
    PlayerDto findOne(int id);
    PlayerDto create(CreatePlayerCommand command);
    PlayerDto update(int id, UpdatePlayerCommand command);
    void delete(int id);
}
