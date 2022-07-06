package hr.bornast.fantasy.application.service.impl;

import java.util.List;

import hr.bornast.fantasy.application.command.season.CreateSeasonCommand;
import hr.bornast.fantasy.application.command.season.UpdateSeasonCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.season.SeasonDto;
import hr.bornast.fantasy.application.repository.SeasonRepository;
import hr.bornast.fantasy.application.service.SeasonService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.common.exception.ValidationException;
import hr.bornast.fantasy.domain.model.Season;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository seasonRepository;
    private final ModelMapper mapper;

    @Override
    public PagedListDto<SeasonDto> findAll(Pageable paging, String name) {
        if (name == null) {
            return new PagedListDto<SeasonDto>().getPagedResult(
                seasonRepository.findAll(paging)
                    .map(x -> mapper.map(x, SeasonDto.class)));
        }

        return new PagedListDto<SeasonDto>().getPagedResult(
            seasonRepository.findByName(name, paging)
                .map(x -> mapper.map(x, SeasonDto.class)));
    }

    @Override
    public List<RecordNameDto> findAllRecordNames() {
        return seasonRepository.findAll().stream().map(x -> mapper.map(x, RecordNameDto.class)).toList();
    }

    @Override
    public SeasonDto findOne(int id) {
        return seasonRepository.findById(id)
            .map(x -> mapper.map(x, SeasonDto.class))
            .orElseThrow(EntityNotFoundException::new);
    }

    public SeasonDto create(CreateSeasonCommand command) {
        seasonRepository.findByName(command.getName())
            .ifPresent(r -> {throw new ValidationException("Season", "season " + command.getName() + " already exists");});

        return mapper.map(seasonRepository.create(mapper.map(command, Season.class)), SeasonDto.class);
    }

    @Override
    public SeasonDto update(int id, UpdateSeasonCommand command) {
        var season = seasonRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        mapper.map(command, season);
        return mapper.map(seasonRepository.update(season), SeasonDto.class);
    }

    @Override
    public void delete(int id) {
        seasonRepository.delete(id);
    }

}
