package hr.bornast.fantasy.application.service.impl;

import java.util.List;

import hr.bornast.fantasy.application.command.coach.CreateCoachCommand;
import hr.bornast.fantasy.application.command.coach.UpdateCoachCommand;
import hr.bornast.fantasy.application.dto.coach.CoachDto;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.repository.CoachRepository;
import hr.bornast.fantasy.application.service.CoachService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.common.exception.ValidationException;
import hr.bornast.fantasy.domain.model.Coach;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;
    private final ModelMapper mapper;

    @Override
    public PagedListDto<CoachDto> findAll(Pageable paging, String name) {
        if (name == null) {
            return new PagedListDto<CoachDto>().getPagedResult(
                coachRepository.findAll(paging)
                    .map(x -> mapper.map(x, CoachDto.class)));
        }

        return new PagedListDto<CoachDto>().getPagedResult(
            coachRepository.findByName(name, paging)
                .map(x -> mapper.map(x, CoachDto.class)));
    }

    @Override
    public List<RecordNameDto> findAllRecordNames() {
        return coachRepository.findAll().stream().map(x -> mapper.map(x, RecordNameDto.class)).toList();
    }

    @Override
    public CoachDto findOne(int id) {
        return coachRepository.findById(id)
            .map(x -> mapper.map(x, CoachDto.class))
            .orElseThrow(EntityNotFoundException::new);
    }

    public CoachDto create(CreateCoachCommand command) {
        coachRepository.findByName(command.getName())
            .ifPresent(r -> {throw new ValidationException("Coach", "coach " + command.getName() + " already exists");});

        return mapper.map(coachRepository.create(mapper.map(command, Coach.class)), CoachDto.class);
    }

    @Override
    public CoachDto update(int id, UpdateCoachCommand command) {
        var coach = coachRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        mapper.map(command, coach);
        return mapper.map(coachRepository.update(coach), CoachDto.class);
    }

    @Override
    public void delete(int id) {
        coachRepository.delete(id);
    }

}
