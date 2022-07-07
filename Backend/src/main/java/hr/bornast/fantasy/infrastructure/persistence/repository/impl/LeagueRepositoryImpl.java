package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.application.repository.LeagueRepository;
import hr.bornast.fantasy.domain.model.League;
import hr.bornast.fantasy.infrastructure.persistence.entity.LeagueEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.LeagueEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LeagueRepositoryImpl implements LeagueRepository {

    private final LeagueEntityRepository leagueRepository;
    private final ModelMapper mapper;

    @Override
    public Page<League> findAll(Pageable paging) {
        return leagueRepository.findAll(paging).map(x -> mapper.map(x, League.class));
    }

    @Override
    public Page<League> findByName(String name, Pageable pageable) {
        return leagueRepository.findByNameContainingIgnoreCase(name, pageable)
            .map(x -> mapper.map(x, League.class));
    }

    @Override
    public List<League> findAll() {
        return leagueRepository.findAll().stream().map(x -> mapper.map(x, League.class)).toList();
    }

    @Override
    public Optional<League> findById(int id) {
        return leagueRepository.findById(id).map(x -> mapper.map(x, League.class));
    }

    @Override
    public Optional<League> findByName(String name) {
        return leagueRepository.findByName(name)
            .map(x -> mapper.map(x, League.class));
    }

    @Override
    public League create(League league) {
        return mapper.map(leagueRepository.save(mapper.map(league, LeagueEntity.class)), League.class);
    }

    @Override
    public void delete(int id) {
        leagueRepository.deleteById(id);
    }

    @Override
    public League update(League league) {
        return mapper.map(leagueRepository.save(mapper.map(league, LeagueEntity.class)), League.class);
    }

}
