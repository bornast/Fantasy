package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.application.repository.MatchRepository;
import hr.bornast.fantasy.domain.model.Match;
import hr.bornast.fantasy.infrastructure.persistence.mapper.MatchEntityMapper;
import hr.bornast.fantasy.infrastructure.persistence.repository.MatchEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MatchRepositoryImpl implements MatchRepository {

    private final MatchEntityRepository matchRepository;
    private final MatchEntityMapper mapper;

    @Override
    public Page<Match> findAll(Pageable paging) {
        return matchRepository.findAll(paging).map(mapper::map);
    }

    @Override
    public Page<Match> findByName(String name, Pageable pageable) {
        return matchRepository.findByTeams_Team_NameContainingIgnoreCase(name, pageable)
            .map(mapper::map);
    }

    @Override
    public List<Match> findAll() {
        return matchRepository.findAll().stream().map(mapper::map).toList();
    }

    @Override
    public Optional<Match> findById(int id) {
        return matchRepository.findById(id).map(mapper::map);
    }

    @Override
    public List<Match> findByIds(List<Integer> ids) {
        return matchRepository.findByIdIn(ids).stream().map(mapper::map).toList();
    }

    @Override
    public Match create(Match match) {
        return mapper.map(matchRepository.save(mapper.map(match)));
    }

    @Override
    public void delete(int id) {
        matchRepository.deleteById(id);
    }

    @Override
    public Match update(Match match) {
        return mapper.map(matchRepository.save(mapper.map(match)));
    }

}
