package hr.bornast.fantasy.application.service;

import java.util.List;

import hr.bornast.fantasy.application.command.position.CreatePositionCommand;
import hr.bornast.fantasy.application.command.position.UpdatePositionCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.position.PositionDto;
import org.springframework.data.domain.Pageable;

public interface PositionService {
    PagedListDto<PositionDto> findAll(Pageable pageable, String name);
    List<RecordNameDto> findAllRecordNames();
    PositionDto findOne(int id);
    PositionDto create(CreatePositionCommand command);
    PositionDto update(int id, UpdatePositionCommand command);
    void delete(int id);
}
