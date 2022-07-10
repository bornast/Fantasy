package hr.bornast.fantasy.application.service.impl;

import java.util.List;

import hr.bornast.fantasy.application.command.match.SaveMatchCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.match.MatchDto;
import hr.bornast.fantasy.application.mapper.MatchMapper;
import hr.bornast.fantasy.application.repository.MatchRepository;
import hr.bornast.fantasy.application.service.MatchService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final MatchMapper mapper;

    @Override
    public PagedListDto<MatchDto> findAll(Pageable paging, String name) {
        if (name == null) {
            return new PagedListDto<MatchDto>().getPagedResult(
                matchRepository.findAll(paging)
                    .map(mapper::map));
        }

        return new PagedListDto<MatchDto>().getPagedResult(
            matchRepository.findByName(name, paging)
                .map(mapper::map));
    }

    @Override
    public List<RecordNameDto> findAllRecordNames() {
        return matchRepository.findAll().stream().map(mapper::mapRecordName).toList();
    }

    @Override
    public MatchDto findOne(int id) {
        return matchRepository.findById(id)
            .map(mapper::map)
            .orElseThrow(EntityNotFoundException::new);
    }

    public MatchDto create(SaveMatchCommand command) {
        return mapper.map(matchRepository.create(mapper.map(command)));
    }

    @Override
    public MatchDto update(int id, SaveMatchCommand command) {
        var match = matchRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        mapper.map(command, match);
        return mapper.map(matchRepository.update(match));
    }

    @Override
    public void delete(int id) {
        matchRepository.delete(id);
    }

}
