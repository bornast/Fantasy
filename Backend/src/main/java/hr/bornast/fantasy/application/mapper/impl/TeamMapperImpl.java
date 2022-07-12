package hr.bornast.fantasy.application.mapper.impl;

import hr.bornast.fantasy.application.command.team.SaveTeamCommand;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.team.TeamDto;
import hr.bornast.fantasy.application.dto.team.TeamResultDto;
import hr.bornast.fantasy.application.mapper.TeamMapper;
import hr.bornast.fantasy.application.repository.CoachRepository;
import hr.bornast.fantasy.application.repository.PresidentRepository;
import hr.bornast.fantasy.application.repository.StadiumRepository;
import hr.bornast.fantasy.application.service.MediaService;
import hr.bornast.fantasy.common.enums.EntityType;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.domain.model.Match;
import hr.bornast.fantasy.domain.model.Team;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamMapperImpl implements TeamMapper {

    private final ModelMapper mapper;
    private final PresidentRepository presidentRepository;
    private final CoachRepository coachRepository;
    private final StadiumRepository stadiumRepository;
    private final MediaService mediaService;

    @Override
    public TeamDto map(Team team) {
        var result = mapper.map(team, TeamDto.class);
        var media = mediaService.getEntityMedia(team.getId(), EntityType.TEAM.getValue());
        result.setMedia(media);
        return result;
    }

    @Override
    public void map(SaveTeamCommand command, Team team) {
        mapper.map(command, team);
        setRelatedData(command, team);
    }

    @Override
    public Team map(SaveTeamCommand command) {
        var result = mapper.map(command, Team.class);
        setRelatedData(command, result);
        return result;
    }

    private void setRelatedData(SaveTeamCommand command, Team team) {
        var president = presidentRepository.findById(command.getPresidentId())
            .orElseThrow(EntityNotFoundException::new);

        var coach = coachRepository.findById(command.getCoachId())
            .orElseThrow(EntityNotFoundException::new);

        var stadium = stadiumRepository.findById(command.getStadiumId())
            .orElseThrow(EntityNotFoundException::new);

        team.setPresident(president);
        team.setCoach(coach);
        team.setStadium(stadium);
    }

    @Override
    public RecordNameDto mapRecordName(Team team) {
        return mapper.map(team, RecordNameDto.class);
    }

    @Override
    public TeamResultDto map(Match match) {
        var result = new TeamResultDto();

        var homeTeam = match.getHomeTeam().getTeam();
        result.setHomeTeamName(homeTeam.getName());
        var homeTeamMedia = mediaService.getEntityMedia(homeTeam.getId(), EntityType.TEAM.getValue());
        result.setHomeTeamImage(homeTeamMedia.getMainMedia());

        var awayTeam = match.getAwayTeam().getTeam();;
        result.setAwayTeamName(awayTeam.getName());
        var awayTeamMedia = mediaService.getEntityMedia(awayTeam.getId(), EntityType.TEAM.getValue());
        result.setAwayTeamImage(awayTeamMedia.getMainMedia());

        result.setStadium(match.getStadium().getName());
        result.setMatchDate(mapper.map(match.getMatchDate(), String.class).replace("T", " "));

        var homeScore = 0;
        var awayScore = 0;
        for (var goal : match.getGoals()) {
            if (match.getHomeTeam().getLineupPlayers().stream().anyMatch(x -> x.getId() == goal.getPlayer().getId())) {
                homeScore += 1;
            }
            else if (match.getHomeTeam().getSubstitutePlayers().stream().anyMatch(x -> x.getId() == goal.getPlayer().getId())) {
                homeScore += 1;
            } else {
                awayScore += 1;
            }
        }
        result.setResult(homeScore + " : " + awayScore);

        result.setLeague(match.getLeague().getName());
        result.setSeason(match.getLeague().getSeason().getName());

        return result;
    }
}
