package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.application.repository.PositionRepository;
import hr.bornast.fantasy.domain.model.Position;
import hr.bornast.fantasy.infrastructure.persistence.entity.PositionEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.PositionEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PositionRepositoryImpl implements PositionRepository {

    private final PositionEntityRepository positionRepository;
    private final ModelMapper mapper;

    @Override
    public Page<Position> findAll(Pageable paging) {
        return positionRepository.findAll(paging).map(x -> mapper.map(x, Position.class));
    }

    @Override
    public Page<Position> findByName(String name, Pageable pageable) {
        return positionRepository.findByNameContainingIgnoreCase(name, pageable)
            .map(x -> mapper.map(x, Position.class));
    }

    @Override
    public List<Position> findAll() {
        return positionRepository.findAll().stream().map(x -> mapper.map(x, Position.class)).toList();
    }

    @Override
    public Optional<Position> findById(int id) {
        return positionRepository.findById(id).map(x -> mapper.map(x, Position.class));
    }

    @Override
    public Optional<Position> findByName(String name) {
        return positionRepository.findByName(name)
            .map(x -> mapper.map(x, Position.class));
    }

    @Override
    public Position create(Position position) {
        return mapper.map(positionRepository.save(mapper.map(position, PositionEntity.class)), Position.class);
    }

    @Override
    public void delete(int id) {
        positionRepository.deleteById(id);
    }

    @Override
    public Position update(Position position) {
        return mapper.map(positionRepository.save(mapper.map(position, PositionEntity.class)), Position.class);
    }

}
