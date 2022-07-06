package hr.bornast.fantasy.application.service.impl;

import java.util.List;

import hr.bornast.fantasy.application.command.position.CreatePositionCommand;

import hr.bornast.fantasy.application.command.position.UpdatePositionCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.position.PositionDto;
import hr.bornast.fantasy.application.repository.PositionRepository;
import hr.bornast.fantasy.application.service.PositionService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.common.exception.ValidationException;
import hr.bornast.fantasy.domain.model.Position;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final ModelMapper mapper;

    @Override
    public PagedListDto<PositionDto> findAll(Pageable paging, String name) {
        if (name == null) {
            return new PagedListDto<PositionDto>().getPagedResult(
                positionRepository.findAll(paging)
                    .map(x -> mapper.map(x, PositionDto.class)));
        }

        return new PagedListDto<PositionDto>().getPagedResult(
            positionRepository.findByName(name, paging)
                .map(x -> mapper.map(x, PositionDto.class)));
    }

    @Override
    public List<RecordNameDto> findAllRecordNames() {
        return positionRepository.findAll().stream().map(x -> mapper.map(x, RecordNameDto.class)).toList();
    }

    @Override
    public PositionDto findOne(int id) {
        return positionRepository.findById(id)
            .map(x -> mapper.map(x, PositionDto.class))
            .orElseThrow(EntityNotFoundException::new);
    }

    public PositionDto create(CreatePositionCommand command) {
        positionRepository.findByName(command.getName())
            .ifPresent(r -> {throw new ValidationException("Position", "position " + command.getName() + " already exists");});

        return mapper.map(positionRepository.create(mapper.map(command, Position.class)), PositionDto.class);
    }

    @Override
    public PositionDto update(int id, UpdatePositionCommand command) {
        var position = positionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        mapper.map(command, position);
        return mapper.map(positionRepository.update(position), PositionDto.class);
    }

    @Override
    public void delete(int id) {
        positionRepository.delete(id);
    }

}
