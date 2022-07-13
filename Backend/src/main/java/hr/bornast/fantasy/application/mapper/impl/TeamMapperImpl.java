package hr.bornast.fantasy.application.mapper.impl;

import hr.bornast.fantasy.application.command.team.SaveTeamCommand;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.team.TeamDto;
import hr.bornast.fantasy.application.dto.team.TeamPlayerDto;
import hr.bornast.fantasy.application.dto.team.TeamResultDto;
import hr.bornast.fantasy.application.dto.team.TeamTransferDto;
import hr.bornast.fantasy.application.mapper.TeamMapper;
import hr.bornast.fantasy.application.repository.CoachRepository;
import hr.bornast.fantasy.application.repository.PresidentRepository;
import hr.bornast.fantasy.application.repository.StadiumRepository;
import hr.bornast.fantasy.application.service.MediaService;
import hr.bornast.fantasy.common.enums.EntityType;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.domain.model.Match;
import hr.bornast.fantasy.domain.model.Player;
import hr.bornast.fantasy.domain.model.Team;
import hr.bornast.fantasy.domain.model.Transfer;
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

        result.setMatchId(match.getId());
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

    @Override
    public TeamTransferDto map(Transfer transfer) {
        var result = new TeamTransferDto();

        var fromTeam = transfer.getFromTeam();
        if (fromTeam != null) {
            result.setFromTeamName(fromTeam.getName());
            var fromTeamMedia = mediaService.getEntityMedia(fromTeam.getId(), EntityType.TEAM.getValue());
            result.setFromTeamImage(fromTeamMedia.getMainMedia());
        } else {
            result.setFromTeamName("No Team");
        }

        var toTeam = transfer.getToTeam();
        result.setToTeamName(toTeam.getName());
        var toTeamMedia = mediaService.getEntityMedia(toTeam.getId(), EntityType.TEAM.getValue());
        result.setToTeamImage(toTeamMedia.getMainMedia());

        var player = transfer.getPlayer();
        result.setPlayerName(player.getName());
        var playerMedia = mediaService.getEntityMedia(player.getId(), EntityType.PLAYER.getValue());
        result.setPlayerImage(playerMedia.getMainMedia());

        result.setTransferDate(mapper.map(transfer.getTransferDate(), String.class).substring(0, 10));

        return result;
    }

    @Override
    public TeamPlayerDto map(Player player) {
        var result = new TeamPlayerDto();

        result.setId(player.getId());
        result.setName(player.getName());
        result.setMatchesPlayed(player.getMatches().size());

        int goals = 0;
        int yellowCards = 0;
        int redCards = 0;
        for (var game : player.getMatches()) {
            goals += game.getGoals().stream().filter(x -> x.getPlayer().getId() == player.getId()).count();
            yellowCards += game.getCards().stream().filter(x -> (x.getPlayer().getId() == player.getId()) && x.getCard().getName().equals("yellow")).count();
            redCards += game.getCards().stream().filter(x -> (x.getPlayer().getId() == player.getId()) && x.getCard().getName().equals("red")).count();
        }

        result.setGoals(goals);
        result.setYellowCards(yellowCards);
        result.setRedCards(redCards);
        var media = mediaService.getEntityMedia(player.getId(), EntityType.PLAYER.getValue());
        result.setImage(media.getMainMedia());

        return result;
    }
}
