package hr.bornast.fantasy.application.repository;

import java.util.Optional;

import hr.bornast.fantasy.domain.model.MediaType;

public interface MediaTypeRepository {
    Optional<MediaType> findById(int id);
    MediaType create(MediaType media);
}
