package hr.bornast.fantasy.application.service.impl;

import java.util.List;

import hr.bornast.fantasy.application.command.league.SaveLeagueCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.league.LeagueDto;
import hr.bornast.fantasy.application.mapper.LeagueMapper;
import hr.bornast.fantasy.application.repository.LeagueRepository;
import hr.bornast.fantasy.application.service.LeagueService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeagueServiceImpl implements LeagueService {

    private final LeagueRepository leagueRepository;
    private final LeagueMapper mapper;

    @Override
    public PagedListDto<LeagueDto> findAll(Pageable paging, String name) {
        if (name == null) {
            return new PagedListDto<LeagueDto>().getPagedResult(
                leagueRepository.findAll(paging)
                    .map(mapper::map));
        }

        return new PagedListDto<LeagueDto>().getPagedResult(
            leagueRepository.findByName(name, paging)
                .map(mapper::map));
    }

    @Override
    public List<RecordNameDto> findAllRecordNames() {
        return leagueRepository.findAll().stream().map(mapper::mapRecordName).toList();
    }

    @Override
    public LeagueDto findOne(int id) {
        return leagueRepository.findById(id)
            .map(mapper::map)
            .orElseThrow(EntityNotFoundException::new);
    }

    public LeagueDto create(SaveLeagueCommand command) {
        return mapper.map(leagueRepository.create(mapper.map(command)));
    }

    @Override
    public LeagueDto update(int id, SaveLeagueCommand command) {
        var league = leagueRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        mapper.map(command, league);
        return mapper.map(leagueRepository.update(league));
    }

    @Override
    public void delete(int id) {
        leagueRepository.delete(id);
    }

}
