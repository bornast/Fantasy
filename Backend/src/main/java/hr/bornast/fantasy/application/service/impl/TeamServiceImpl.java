package hr.bornast.fantasy.application.service.impl;

import java.util.ArrayList;
import java.util.List;

import hr.bornast.fantasy.application.command.team.SaveTeamCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.league.LeagueDto;
import hr.bornast.fantasy.application.dto.team.TeamDto;
import hr.bornast.fantasy.application.dto.team.TeamPlayerDto;
import hr.bornast.fantasy.application.dto.team.TeamResultDto;
import hr.bornast.fantasy.application.dto.team.TeamTableDto;
import hr.bornast.fantasy.application.dto.team.TeamTransferDto;
import hr.bornast.fantasy.application.mapper.TeamMapper;
import hr.bornast.fantasy.application.repository.LeagueRepository;
import hr.bornast.fantasy.application.repository.MatchRepository;
import hr.bornast.fantasy.application.repository.PlayerRepository;
import hr.bornast.fantasy.application.repository.TeamRepository;
import hr.bornast.fantasy.application.repository.TransferRepository;
import hr.bornast.fantasy.application.repository.UserRepository;
import hr.bornast.fantasy.application.service.TeamService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.domain.model.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final TransferRepository transferRepository;
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;
    private final LeagueRepository leagueRepository;
    private final TeamMapper mapper;

    @Override
    public PagedListDto<TeamDto> findAll(Pageable paging, String name) {
        if (name == null) {
            return new PagedListDto<TeamDto>().getPagedResult(
                teamRepository.findAll(paging)
                    .map(mapper::map));
        }

        return new PagedListDto<TeamDto>().getPagedResult(
            teamRepository.findByName(name, paging)
                .map(mapper::map));
    }

    @Override
    public List<RecordNameDto> findAllRecordNames() {
        return teamRepository.findAll().stream().map(mapper::mapRecordName).toList();
    }

    @Override
    public List<TeamPlayerDto> findTeamPlayers(int id) {
        var result = new ArrayList<TeamPlayerDto>();
        var transferedPlayers = playerRepository.findAllPlayersInTransfer(id);

        for (var transferPlayer : transferedPlayers) {
            var lastPlayerTransfer = transferRepository.findLastPlayerTransfer(transferPlayer.getId())
                .orElseThrow(EntityNotFoundException::new);
            if (lastPlayerTransfer.getToTeam().getId() == id) {
                var playerToAdd = mapper.map(transferPlayer);
//                var playerToAdd = new RecordNameDto();
//                // TODO: map Player class to TeamPlayerDto
//                playerToAdd.setId(lastPlayerTransfer.getPlayer().getId());
//                playerToAdd.setName(lastPlayerTransfer.getPlayer().getName());
                result.add(playerToAdd);
            }
        }

        return result;
    }

    @Override
    public TeamDto findOne(int id) {
        return teamRepository.findById(id)
            .map(mapper::map)
            .orElseThrow(EntityNotFoundException::new);
    }

    public TeamDto create(SaveTeamCommand command) {
        return mapper.map(teamRepository.create(mapper.map(command)));
    }

    @Override
    public TeamDto update(int id, SaveTeamCommand command) {
        var team = teamRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        mapper.map(command, team);
        return mapper.map(teamRepository.update(team));
    }

    @Override
    public void delete(int id) {
        teamRepository.delete(id);
    }

    @Override
    public void setFavourite(int teamId) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepository.findByUsername(username)
            .orElseThrow(EntityNotFoundException::new);

        var team = teamRepository.findById(teamId)
            .orElseThrow(EntityNotFoundException::new);

        team.addFavouredUser(user);

        teamRepository.update(team);
    }

    @Override
    public void setUnfavored(int teamId) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepository.findByUsername(username)
            .orElseThrow(EntityNotFoundException::new);

        var team = teamRepository.findById(teamId)
            .orElseThrow(EntityNotFoundException::new);

        var favouriteToRemove = team.getFavoredUsers().stream()
            .filter(x -> x.getId() == user.getId())
            .findFirst()
            .orElseThrow(EntityNotFoundException::new);

        team.getFavoredUsers().remove(favouriteToRemove);

        teamRepository.update(team);
    }

    @Override
    public PagedListDto<TeamDto> findFavourites(Pageable paging, String name) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepository.findByUsername(username)
            .orElseThrow(EntityNotFoundException::new);

        if (name == null) {
            return new PagedListDto<TeamDto>().getPagedResult(
                teamRepository.findFavouriteTeams(user.getId(), paging)
                    .map(mapper::map));
        }

        return new PagedListDto<TeamDto>().getPagedResult(
            teamRepository.findFavouriteTeams(user.getId(), name, paging)
                .map(mapper::map));
    }

    @Override
    public PagedListDto<TeamDto> findUnfavored(Pageable paging, String name) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepository.findByUsername(username)
            .orElseThrow(EntityNotFoundException::new);

        List<Team> favouriteTeams = new ArrayList<Team>();

        if (name == null) {
            favouriteTeams.addAll(teamRepository.findFavouriteTeams(user.getId()));
        } else {
            favouriteTeams.addAll(teamRepository.findFavouriteTeams(user.getId(), name));
        }

        var allTeams = teamRepository.findAllByName(name);

        var unfavoredTeams = allTeams.stream()
            .filter(x -> !favouriteTeams.contains(x))
            .map(mapper::map)
            .toList();

        int start = Math.min((int)paging.getOffset(), unfavoredTeams.size());
        int end = Math.min((start + paging.getPageSize()), unfavoredTeams.size());

        Page<TeamDto> page = new PageImpl<>(unfavoredTeams.subList(start, end), paging, unfavoredTeams.size());

        return new PagedListDto<TeamDto>().getPagedResult(page);
    }

    @Override
    public PagedListDto<TeamResultDto> findTeamResults(int teamId, Pageable paging) {
        return new PagedListDto<TeamResultDto>().getPagedResult(
            matchRepository.findByTeamId(teamId, paging)
                .map(mapper::map));
    }

    @Override
    public PagedListDto<TeamTransferDto> findTeamTransfers(int teamId, Pageable paging) {
        return new PagedListDto<TeamTransferDto>().getPagedResult(
            transferRepository.findByTeamId(teamId, paging)
                .map(mapper::map));
    }

    @Override
    public List<TeamTableDto> getTeamTable(int leagueId) {
        var league = leagueRepository.findById(leagueId)
            .orElseThrow(EntityNotFoundException::new);

        return mapper.map(league);
    }

    @Override
    public List<LeagueDto> getTeamLeagues(int id) {
        return leagueRepository.findByTeamId(id)
            .stream().map(mapper::mapTeamLeague).toList();
    }

}
