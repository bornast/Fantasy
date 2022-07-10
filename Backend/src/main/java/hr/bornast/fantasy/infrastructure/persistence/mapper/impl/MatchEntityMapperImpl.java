package hr.bornast.fantasy.infrastructure.persistence.mapper.impl;

import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.domain.model.Formation;
import hr.bornast.fantasy.domain.model.League;
import hr.bornast.fantasy.domain.model.Match;
import hr.bornast.fantasy.domain.model.MatchCard;
import hr.bornast.fantasy.domain.model.MatchGoal;
import hr.bornast.fantasy.domain.model.MatchSubstitution;
import hr.bornast.fantasy.domain.model.MatchTeam;
import hr.bornast.fantasy.domain.model.Player;
import hr.bornast.fantasy.domain.model.Referee;
import hr.bornast.fantasy.domain.model.Stadium;
import hr.bornast.fantasy.domain.model.Team;
import hr.bornast.fantasy.infrastructure.persistence.entity.LeagueEntity;
import hr.bornast.fantasy.infrastructure.persistence.entity.MatchCardEntity;
import hr.bornast.fantasy.infrastructure.persistence.entity.MatchEntity;
import hr.bornast.fantasy.infrastructure.persistence.entity.MatchGoalEntity;
import hr.bornast.fantasy.infrastructure.persistence.entity.MatchPlayerEntity;
import hr.bornast.fantasy.infrastructure.persistence.entity.MatchSubstitutionEntity;
import hr.bornast.fantasy.infrastructure.persistence.entity.MatchTeamEntity;
import hr.bornast.fantasy.infrastructure.persistence.entity.PlayerEntity;
import hr.bornast.fantasy.infrastructure.persistence.entity.RefereeEntity;
import hr.bornast.fantasy.infrastructure.persistence.entity.StadiumEntity;
import hr.bornast.fantasy.infrastructure.persistence.entity.TeamEntity;
import hr.bornast.fantasy.infrastructure.persistence.mapper.MatchEntityMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchEntityMapperImpl implements MatchEntityMapper {

    private final ModelMapper mapper;

    @Override
    public Match map(MatchEntity matchEntity) {
        var result = new Match();
        for (var team : matchEntity.getTeams()) {
            var matchTeam = new MatchTeam();
            matchTeam.setTeam(mapper.map(team.getTeam(), Team.class));
            matchTeam.setFormation(mapper.map(team.getFormation(), Formation.class));
            if (team.isHomeTeam()) {
                result.setHomeTeam(matchTeam);
            } else {
                result.setAwayTeam(matchTeam);
            }
        }

        for (var player : matchEntity.getPlayers()) {
            var playerTeam = matchEntity.getTeams().stream()
                .filter(x -> x.getTeam().getId() == player.getTeam().getId())
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);

            if (playerTeam.isHomeTeam()) {
                if (player.isLineupPlayer()) {
                    result.getHomeTeam().addLineupPlayer(mapper.map(player.getPlayer(), Player.class));
                } else {
                    result.getHomeTeam().addSubstitutePlayer(mapper.map(player.getPlayer(), Player.class));
                }
            } else {
                if (player.isLineupPlayer()) {
                    result.getAwayTeam().addLineupPlayer(mapper.map(player.getPlayer(), Player.class));
                } else {
                    result.getAwayTeam().addSubstitutePlayer(mapper.map(player.getPlayer(), Player.class));
                }
            }
        }

        for (var substitution : matchEntity.getSubstitutions()) {
            var substitutionTeam = matchEntity.getTeams().stream()
                .filter(x -> x.getTeam().getId() == substitution.getTeam().getId())
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);

            var matchSubstitution = new MatchSubstitution();
            matchSubstitution.setMinute(substitution.getMinute());
            matchSubstitution.setLineupPlayer(mapper.map(substitution.getLineupPlayer(), Player.class));
            matchSubstitution.setSubstitutePlayer(mapper.map(substitution.getSubstitutePlayer(), Player.class));

            if (substitutionTeam.isHomeTeam()) {
                result.getHomeTeam().addSubstitutions(matchSubstitution);
            } else {
                result.getAwayTeam().addSubstitutions(matchSubstitution);
            }
        }

        for (var goal : matchEntity.getGoals()) {
            result.addGoal(mapper.map(goal, MatchGoal.class));
        }

        for (var card : matchEntity.getCards()) {
            result.addCard(mapper.map(card, MatchCard.class));
        }

        result.setId(matchEntity.getId());
        result.setLeague(mapper.map(matchEntity.getLeague(), League.class));
        result.setMatchDate(matchEntity.getMatchDate());
        result.setReferee(mapper.map(matchEntity.getReferee(), Referee.class));
        result.setStadium(mapper.map(matchEntity.getStadium(), Stadium.class));
        result.setSpectatorCount(matchEntity.getSpectatorCount());

        return result;
    }

    @Override
    public MatchEntity map(Match match) {
        var result = new MatchEntity();
        result.setId(match.getId());

        addTeam(match.getHomeTeam(), result, true);
        addTeam(match.getAwayTeam(), result, false);

        for (var matchGoal : match.getGoals()) {
            result.addMatchGoal(mapper.map(matchGoal, MatchGoalEntity.class));
        }

        for (var matchCard : match.getCards()) {
            result.addMatchCard(mapper.map(matchCard, MatchCardEntity.class));
        }

        result.setLeague(mapper.map(match.getLeague(), LeagueEntity.class));
        result.setReferee(mapper.map(match.getReferee(), RefereeEntity.class));
        result.setStadium(mapper.map(match.getStadium(), StadiumEntity.class));
        result.setSpectatorCount(match.getSpectatorCount());
        result.setMatchDate(match.getMatchDate());

        addMatchPlayers(match.getHomeTeam(), result);
        addMatchPlayers(match.getAwayTeam(), result);

        addMatchSubstitutions(match.getHomeTeam(), result);
        addMatchSubstitutions(match.getAwayTeam(), result);

        return result;
    }

    private void addTeam(MatchTeam match, MatchEntity result, boolean isHomeTeam) {
        var team = mapper.map(match, MatchTeamEntity.class);
        team.setHomeTeam(isHomeTeam);
        result.addMatchTeam(team);
    }

    private void addMatchPlayers(MatchTeam team, MatchEntity result) {
        for (var player : team.getLineupPlayers()) {
            result.addMatchPlayer(getMatchPlayer(player, team, true));
        }

        for (var player : team.getSubstitutePlayers()) {
            result.addMatchPlayer(getMatchPlayer(player, team, false));
        }
    }

    private MatchPlayerEntity getMatchPlayer(Player player, MatchTeam team, boolean isLineupPlayer) {
        var matchPlayerEntity = new MatchPlayerEntity();
        matchPlayerEntity.setTeam(mapper.map(team.getTeam(), TeamEntity.class));
        matchPlayerEntity.setLineupPlayer(isLineupPlayer);
        matchPlayerEntity.setPlayer(mapper.map(player, PlayerEntity.class));
        return matchPlayerEntity;
    }

    private void addMatchSubstitutions(MatchTeam matchTeam, MatchEntity result) {
        for (var substitution : matchTeam.getSubstitutions()) {
            var matchSubstitution = new MatchSubstitutionEntity();
            matchSubstitution.setTeam(mapper.map(matchTeam.getTeam(), TeamEntity.class));
            matchSubstitution.setLineupPlayer(mapper.map(substitution.getLineupPlayer(), PlayerEntity.class));
            matchSubstitution.setSubstitutePlayer(mapper.map(substitution.getSubstitutePlayer(), PlayerEntity.class));
            matchSubstitution.setMinute(substitution.getMinute());
            result.addMatchSubstitution(matchSubstitution);
        }
    }

}

