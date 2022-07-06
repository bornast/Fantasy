package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.Optional;

import hr.bornast.fantasy.application.repository.EntityTypeRepository;
import hr.bornast.fantasy.domain.model.EntityType;
import hr.bornast.fantasy.infrastructure.persistence.entity.EntityTypeEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.EntityTypeEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EntityTypeRepositoryImpl implements EntityTypeRepository {

    private final EntityTypeEntityRepository entityRepository;
    private final ModelMapper mapper;

    @Override
    public Optional<EntityType> findById(int id) {
        return entityRepository.findById(id).map(x -> mapper.map(x, EntityType.class));
    }

    @Override
    public EntityType create(EntityType entityType) {
        return mapper.map(entityRepository.save(mapper.map(entityType, EntityTypeEntity.class)), EntityType.class);
    }

}
