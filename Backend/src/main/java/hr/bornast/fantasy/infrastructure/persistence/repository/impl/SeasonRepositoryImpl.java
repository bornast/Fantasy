package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.application.repository.SeasonRepository;
import hr.bornast.fantasy.domain.model.Season;
import hr.bornast.fantasy.infrastructure.persistence.entity.SeasonEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.SeasonEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SeasonRepositoryImpl implements SeasonRepository {

    private final SeasonEntityRepository seasonRepository;
    private final ModelMapper mapper;

    @Override
    public Page<Season> findAll(Pageable paging) {
        return seasonRepository.findAll(paging).map(x -> mapper.map(x, Season.class));
    }

    @Override
    public Page<Season> findByName(String name, Pageable pageable) {
        return seasonRepository.findByNameContainingIgnoreCase(name, pageable)
            .map(x -> mapper.map(x, Season.class));
    }

    @Override
    public List<Season> findAll() {
        return seasonRepository.findAll().stream().map(x -> mapper.map(x, Season.class)).toList();
    }

    @Override
    public Optional<Season> findById(int id) {
        return seasonRepository.findById(id).map(x -> mapper.map(x, Season.class));
    }

    @Override
    public Optional<Season> findByName(String name) {
        return seasonRepository.findByName(name)
            .map(x -> mapper.map(x, Season.class));
    }

    @Override
    public Season create(Season season) {
        return mapper.map(seasonRepository.save(mapper.map(season, SeasonEntity.class)), Season.class);
    }

    @Override
    public void delete(int id) {
        seasonRepository.deleteById(id);
    }

    @Override
    public Season update(Season season) {
        return mapper.map(seasonRepository.save(mapper.map(season, SeasonEntity.class)), Season.class);
    }

}
