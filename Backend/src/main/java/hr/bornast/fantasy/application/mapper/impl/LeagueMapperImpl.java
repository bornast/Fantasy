package hr.bornast.fantasy.application.mapper.impl;

import hr.bornast.fantasy.application.command.league.SaveLeagueCommand;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.league.LeagueDto;
import hr.bornast.fantasy.application.mapper.LeagueMapper;
import hr.bornast.fantasy.application.repository.SeasonRepository;
import hr.bornast.fantasy.application.repository.TeamRepository;
import hr.bornast.fantasy.application.service.MediaService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.domain.model.League;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeagueMapperImpl implements LeagueMapper {

    private final ModelMapper mapper;
    private final SeasonRepository seasonRepository;
    private final TeamRepository teamRepository;
    private final MediaService mediaService;

    @Override
    public LeagueDto map(League league) {
        var result = mapper.map(league, LeagueDto.class);
        return result;
    }

    @Override
    public void map(SaveLeagueCommand command, League league) {
        mapper.map(command, league);
        setRelatedData(command, league);
    }

    @Override
    public League map(SaveLeagueCommand command) {
        var result = mapper.map(command, League.class);
        setRelatedData(command, result);
        return result;
    }

    private void setRelatedData(SaveLeagueCommand command, League league) {
        var season = seasonRepository.findById(command.getSeasonId())
            .orElseThrow(EntityNotFoundException::new);

        league.setSeason(season);

        // remove teams
        var teamsToRemove = league.getTeams().stream()
            .filter(x -> !command.getTeamIds().contains(x.getId()))
            .toList();

        for (var teamToRemove : teamsToRemove) {
            league.getTeams().remove(teamToRemove);
        }

        // add teams
        var teamsToAddIds = command.getTeamIds().stream()
            .filter(teamId -> league.getTeams().stream().noneMatch(x -> x.getId() == teamId)).toList();

        teamRepository.findByIds(teamsToAddIds).forEach(league::addTeam);
    }

    @Override
    public RecordNameDto mapRecordName(League league) {
        return mapper.map(league, RecordNameDto.class);
    }
}
