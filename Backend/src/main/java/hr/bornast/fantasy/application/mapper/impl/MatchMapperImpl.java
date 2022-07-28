package hr.bornast.fantasy.application.mapper.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hr.bornast.fantasy.application.command.match.SaveMatchCardCommand;
import hr.bornast.fantasy.application.command.match.SaveMatchCommand;
import hr.bornast.fantasy.application.command.match.SaveMatchGoalCommand;
import hr.bornast.fantasy.application.command.match.SaveMatchTeamCommand;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.match.MatchDto;
import hr.bornast.fantasy.application.mapper.MatchMapper;
import hr.bornast.fantasy.application.repository.CardRepository;
import hr.bornast.fantasy.application.repository.FormationRepository;
import hr.bornast.fantasy.application.repository.LeagueRepository;
import hr.bornast.fantasy.application.repository.PlayerRepository;
import hr.bornast.fantasy.application.repository.RateRepository;
import hr.bornast.fantasy.application.repository.RefereeRepository;
import hr.bornast.fantasy.application.repository.StadiumRepository;
import hr.bornast.fantasy.application.repository.TeamRepository;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.domain.model.Match;
import hr.bornast.fantasy.domain.model.MatchCard;
import hr.bornast.fantasy.domain.model.MatchGoal;
import hr.bornast.fantasy.domain.model.MatchSubstitution;
import hr.bornast.fantasy.domain.model.MatchTeam;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchMapperImpl implements MatchMapper {

    private final ModelMapper mapper;
    private final TeamRepository teamRepository;
    private final FormationRepository formationRepository;
    private final PlayerRepository playerRepository;
    private final CardRepository cardRepository;
    private final LeagueRepository leagueRepository;
    private final RefereeRepository refereeRepository;
    private final StadiumRepository stadiumRepository;
    private final RateRepository rateRepository;

    @Override
    public MatchDto map(Match match) {
        var result = mapper.map(match, MatchDto.class);

        result.setName(result.getHomeTeam().getTeam().getName() + " vs " + result.getAwayTeam().getTeam().getName());

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
        result.getHomeTeam().setCoach(mapper.map(match.getHomeTeam().getTeam().getCoach(), RecordNameDto.class));
        result.getAwayTeam().setCoach(mapper.map(match.getAwayTeam().getTeam().getCoach(), RecordNameDto.class));

        result.setResult(homeScore + " : " + awayScore);

        result.getHomeTeam().getLineupPlayers().forEach(x -> {
            x.setRate(rateRepository.findPlayerMatchRate(x.getId(), match.getId()));
        });

        result.getHomeTeam().getSubstitutePlayers().forEach(x -> {
            x.setRate(rateRepository.findPlayerMatchRate(x.getId(), match.getId()));
        });

        result.getAwayTeam().getLineupPlayers().forEach(x -> {
            x.setRate(rateRepository.findPlayerMatchRate(x.getId(), match.getId()));
        });

        result.getAwayTeam().getSubstitutePlayers().forEach(x -> {
            x.setRate(rateRepository.findPlayerMatchRate(x.getId(), match.getId()));
        });

        return result;
    }

    @Override
    public void map(SaveMatchCommand command, Match match) {
        mapper.map(command, match);
        setRelatedData(command, match);
    }

    @Override
    public Match map(SaveMatchCommand command) {
        var result = mapper.map(command, Match.class);
        setRelatedData(command, result);
        return result;
    }

    private void setRelatedData(SaveMatchCommand command, Match match) {
        var homeTeam = new MatchTeam();
        setTeamRelatedData(command.getHomeTeam(), homeTeam);
        match.setHomeTeam(homeTeam);

        var awayTeam = new MatchTeam();
        setTeamRelatedData(command.getAwayTeam(), awayTeam);
        match.setAwayTeam(awayTeam);

        match.setGoals(setGoalRelatedData(command.getGoals()));

        match.setCards(setCardRelatedData(command.getCards()));

        var league = leagueRepository.findById(command.getLeagueId())
            .orElseThrow(EntityNotFoundException::new);
        match.setLeague(league);

        var referee = refereeRepository.findById(command.getRefereeId())
            .orElseThrow(EntityNotFoundException::new);
        match.setReferee(referee);

        var stadium = stadiumRepository.findById(command.getStadiumId())
            .orElseThrow(EntityNotFoundException::new);
        match.setStadium(stadium);
    }

    private void setTeamRelatedData(SaveMatchTeamCommand command, MatchTeam matchTeam) {
        var team = teamRepository.findById(command.getTeamId())
            .orElseThrow(EntityNotFoundException::new);
        matchTeam.setTeam(team);

        var formation = formationRepository.findById(command.getFormationId())
            .orElseThrow(EntityNotFoundException::new);
        matchTeam.setFormation(formation);

        var lineupPlayers = playerRepository.findByIds(command.getLineupPlayerIds());
        matchTeam.setLineupPlayers(lineupPlayers);

        var substitutePlayers = playerRepository.findByIds(command.getSubstitutePlayerIds());
        matchTeam.setSubstitutePlayers(substitutePlayers);

        List<MatchSubstitution> matchSubstitutions = new ArrayList<>();
        for (var substitution : command.getSubstitutions()) {
            var matchSubstitution = new MatchSubstitution();
            matchSubstitution.setMinute(substitution.getMinute());

            var lineupPlayer = playerRepository.findById(substitution.getLineupPlayerId())
                .orElseThrow(EntityNotFoundException::new);
            matchSubstitution.setLineupPlayer(lineupPlayer);

            var substitutePlayer = playerRepository.findById(substitution.getSubstitutePlayerId())
                .orElseThrow(EntityNotFoundException::new);
            matchSubstitution.setSubstitutePlayer(substitutePlayer);

            matchSubstitutions.add(matchSubstitution);
        }

        matchTeam.setSubstitutions(matchSubstitutions);
    }

    private Set<MatchGoal> setGoalRelatedData(Set<SaveMatchGoalCommand> goalCommand) {
        var result = new HashSet<MatchGoal>();
        for (var goal : goalCommand) {
            var matchGoal = new MatchGoal();
            matchGoal.setMinute(goal.getMinute());

            var matchGoalPlayer = playerRepository.findById(goal.getPlayerId())
                .orElseThrow(EntityNotFoundException::new);
            matchGoal.setPlayer(matchGoalPlayer);

            result.add(matchGoal);
        }
        return result;
    }

    private Set<MatchCard> setCardRelatedData(Set<SaveMatchCardCommand> cardCommand) {
        var result = new HashSet<MatchCard>();
        for (var card : cardCommand) {
            var matchCard = new MatchCard();

            var matchCardEntity = cardRepository.findById(card.getCardId())
                .orElseThrow(EntityNotFoundException::new);
            matchCard.setCard(matchCardEntity);

            matchCard.setMinute(card.getMinute());

            var matchCardPlayer = playerRepository.findById(card.getPlayerId())
                .orElseThrow(EntityNotFoundException::new);
            matchCard.setPlayer(matchCardPlayer);

            result.add(matchCard);
        }
        return result;
    }

    @Override
    public RecordNameDto mapRecordName(Match match) {
        var result = new RecordNameDto();
        result.setId(match.getId());
        result.setName(match.getHomeTeam().getTeam().getName() + " vs " + match.getAwayTeam().getTeam().getName());
        return result;
    }
}
