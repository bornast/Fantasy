package hr.bornast.fantasy.application.service;

import java.util.List;

import hr.bornast.fantasy.application.command.coach.CreateCoachCommand;
import hr.bornast.fantasy.application.command.coach.UpdateCoachCommand;
import hr.bornast.fantasy.application.dto.coach.CoachDto;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import org.springframework.data.domain.Pageable;

public interface CoachService {
    PagedListDto<CoachDto> findAll(Pageable pageable, String name);
    List<RecordNameDto> findAllRecordNames();
    CoachDto findOne(int id);
    CoachDto create(CreateCoachCommand command);
    CoachDto update(int id, UpdateCoachCommand command);
    void delete(int id);
}
