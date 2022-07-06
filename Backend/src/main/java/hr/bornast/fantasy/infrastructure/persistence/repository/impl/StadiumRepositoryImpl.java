package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.application.repository.StadiumRepository;
import hr.bornast.fantasy.domain.model.Stadium;
import hr.bornast.fantasy.infrastructure.persistence.entity.StadiumEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.StadiumEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StadiumRepositoryImpl implements StadiumRepository {

    private final StadiumEntityRepository stadiumRepository;
    private final ModelMapper mapper;

    @Override
    public Page<Stadium> findAll(Pageable paging) {
        return stadiumRepository.findAll(paging).map(x -> mapper.map(x, Stadium.class));
    }

    @Override
    public Page<Stadium> findByName(String name, Pageable pageable) {
        return stadiumRepository.findByNameContainingIgnoreCase(name, pageable)
            .map(x -> mapper.map(x, Stadium.class));
    }

    @Override
    public List<Stadium> findAll() {
        return stadiumRepository.findAll().stream().map(x -> mapper.map(x, Stadium.class)).toList();
    }

    @Override
    public Optional<Stadium> findById(int id) {
        return stadiumRepository.findById(id).map(x -> mapper.map(x, Stadium.class));
    }

    @Override
    public Optional<Stadium> findByName(String name) {
        return stadiumRepository.findByName(name)
            .map(x -> mapper.map(x, Stadium.class));
    }

    @Override
    public Stadium create(Stadium stadium) {
        return mapper.map(stadiumRepository.save(mapper.map(stadium, StadiumEntity.class)), Stadium.class);
    }

    @Override
    public void delete(int id) {
        stadiumRepository.deleteById(id);
    }

    @Override
    public Stadium update(Stadium stadium) {
        return mapper.map(stadiumRepository.save(mapper.map(stadium, StadiumEntity.class)), Stadium.class);
    }

}
