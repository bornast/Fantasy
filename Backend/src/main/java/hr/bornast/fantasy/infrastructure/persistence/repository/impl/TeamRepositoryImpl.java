package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.application.repository.TeamRepository;
import hr.bornast.fantasy.domain.model.Team;
import hr.bornast.fantasy.infrastructure.persistence.entity.TeamEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.TeamEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamRepository {

    private final TeamEntityRepository teamRepository;
    private final ModelMapper mapper;

    @Override
    public Page<Team> findAll(Pageable paging) {
        return teamRepository.findAll(paging).map(x -> mapper.map(x, Team.class));
    }

    @Override
    public List<Team> findAllByName(String name) {
        return teamRepository.findAll(name).stream().map(x -> mapper.map(x, Team.class)).toList();
    }

    @Override
    public Page<Team> findByName(String name, Pageable pageable) {
        return teamRepository.findByNameContainingIgnoreCase(name, pageable)
            .map(x -> mapper.map(x, Team.class));
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll().stream().map(x -> mapper.map(x, Team.class)).toList();
    }

    @Override
    public Optional<Team> findById(int id) {
        return teamRepository.findById(id).map(x -> mapper.map(x, Team.class));
    }

    @Override
    public Optional<Team> findByName(String name) {
        return teamRepository.findByName(name)
            .map(x -> mapper.map(x, Team.class));
    }

    @Override
    public List<Team> findByIds(List<Integer> ids) {
        return teamRepository.findByIdIn(ids).stream().map(x -> mapper.map(x, Team.class)).toList();
    }

    @Override
    public Team create(Team team) {
        return mapper.map(teamRepository.save(mapper.map(team, TeamEntity.class)), Team.class);
    }

    @Override
    public void delete(int id) {
        teamRepository.deleteById(id);
    }

    @Override
    public Page<Team> findFavouriteTeams(int userId, Pageable paging) {
        return teamRepository.findFavouriteTeams(userId, paging)
            .map(x -> mapper.map(x, Team.class));
    }

    @Override
    public Page<Team> findFavouriteTeams(int userId, String name, Pageable paging) {
        return teamRepository.findFavouriteTeams(userId, name, paging)
            .map(x -> mapper.map(x, Team.class));
    }

    @Override
    public List<Team> findFavouriteTeams(int userId) {
        return teamRepository.findFavouriteTeams(userId).stream()
            .map(x -> mapper.map(x, Team.class)).toList();
    }

    @Override
    public List<Team> findFavouriteTeams(int userId, String name) {
        return teamRepository.findFavouriteTeams(userId, name).stream()
            .map(x -> mapper.map(x, Team.class)).toList();
    }

    @Override
    public Team update(Team team) {
        return mapper.map(teamRepository.save(mapper.map(team, TeamEntity.class)), Team.class);
    }

}
