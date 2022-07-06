package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.application.repository.PresidentRepository;
import hr.bornast.fantasy.domain.model.President;
import hr.bornast.fantasy.infrastructure.persistence.entity.PresidentEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.PresidentEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PresidentRepositoryImpl implements PresidentRepository {

    private final PresidentEntityRepository presidentRepository;
    private final ModelMapper mapper;

    @Override
    public Page<President> findAll(Pageable paging) {
        return presidentRepository.findAll(paging).map(x -> mapper.map(x, President.class));
    }

    @Override
    public Page<President> findByName(String name, Pageable pageable) {
        return presidentRepository.findByNameContainingIgnoreCase(name, pageable)
            .map(x -> mapper.map(x, President.class));
    }

    @Override
    public List<President> findAll() {
        return presidentRepository.findAll().stream().map(x -> mapper.map(x, President.class)).toList();
    }

    @Override
    public Optional<President> findById(int id) {
        return presidentRepository.findById(id).map(x -> mapper.map(x, President.class));
    }

    @Override
    public Optional<President> findByName(String name) {
        return presidentRepository.findByName(name)
            .map(x -> mapper.map(x, President.class));
    }

    @Override
    public President create(President president) {
        return mapper.map(presidentRepository.save(mapper.map(president, PresidentEntity.class)), President.class);
    }

    @Override
    public void delete(int id) {
        presidentRepository.deleteById(id);
    }

    @Override
    public President update(President president) {
        return mapper.map(presidentRepository.save(mapper.map(president, PresidentEntity.class)), President.class);
    }

}
