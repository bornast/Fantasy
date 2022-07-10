package hr.bornast.fantasy.application.service.impl;

import java.util.ArrayList;
import java.util.List;

import hr.bornast.fantasy.application.command.team.SaveTeamCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.team.TeamDto;
import hr.bornast.fantasy.application.mapper.TeamMapper;
import hr.bornast.fantasy.application.repository.PlayerRepository;
import hr.bornast.fantasy.application.repository.TeamRepository;
import hr.bornast.fantasy.application.repository.TransferRepository;
import hr.bornast.fantasy.application.service.TeamService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final TransferRepository transferRepository;
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
    public List<RecordNameDto> findTeamPlayers(int id) {
        var result = new ArrayList<RecordNameDto>();
        var transferedPlayers = playerRepository.findAllPlayersInTransfer(id);

        for (var transferPlayer : transferedPlayers) {
            var lastPlayerTransfer = transferRepository.findLastPlayerTransfer(transferPlayer.getId())
                .orElseThrow(EntityNotFoundException::new);
            if (lastPlayerTransfer.getToTeam().getId() == id) {
                var playerToAdd = new RecordNameDto();
                playerToAdd.setId(lastPlayerTransfer.getPlayer().getId());
                playerToAdd.setName(lastPlayerTransfer.getPlayer().getName());
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

}
