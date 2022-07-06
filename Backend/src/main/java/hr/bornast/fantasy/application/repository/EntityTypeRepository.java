package hr.bornast.fantasy.application.repository;

import java.util.Optional;

import hr.bornast.fantasy.domain.model.EntityType;

public interface EntityTypeRepository {
    Optional<EntityType> findById(int id);
    EntityType create(EntityType user);
}
