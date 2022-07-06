package hr.bornast.fantasy.application.service;

import java.util.List;

import hr.bornast.fantasy.application.command.stadium.CreateStadiumCommand;
import hr.bornast.fantasy.application.command.stadium.UpdateStadiumCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.stadium.StadiumDto;
import org.springframework.data.domain.Pageable;

public interface StadiumService {
    PagedListDto<StadiumDto> findAll(Pageable pageable, String name);
    List<RecordNameDto> findAllRecordNames();
    StadiumDto findOne(int id);
    StadiumDto create(CreateStadiumCommand command);
    StadiumDto update(int id, UpdateStadiumCommand command);
    void delete(int id);
}
