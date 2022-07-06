package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.application.repository.CoachRepository;
import hr.bornast.fantasy.domain.model.Coach;
import hr.bornast.fantasy.infrastructure.persistence.entity.CoachEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.CoachEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CoachRepositoryImpl implements CoachRepository {

    private final CoachEntityRepository coachRepository;
    private final ModelMapper mapper;

    @Override
    public Page<Coach> findAll(Pageable paging) {
        return coachRepository.findAll(paging).map(x -> mapper.map(x, Coach.class));
    }

    @Override
    public Page<Coach> findByName(String name, Pageable pageable) {
        return coachRepository.findByNameContainingIgnoreCase(name, pageable)
            .map(x -> mapper.map(x, Coach.class));
    }

    @Override
    public List<Coach> findAll() {
        return coachRepository.findAll().stream().map(x -> mapper.map(x, Coach.class)).toList();
    }

    @Override
    public Optional<Coach> findById(int id) {
        return coachRepository.findById(id).map(x -> mapper.map(x, Coach.class));
    }

    @Override
    public Optional<Coach> findByName(String name) {
        return coachRepository.findByName(name)
            .map(x -> mapper.map(x, Coach.class));
    }

    @Override
    public Coach create(Coach coach) {
        return mapper.map(coachRepository.save(mapper.map(coach, CoachEntity.class)), Coach.class);
    }

    @Override
    public void delete(int id) {
        coachRepository.deleteById(id);
    }

    @Override
    public Coach update(Coach coach) {
        return mapper.map(coachRepository.save(mapper.map(coach, CoachEntity.class)), Coach.class);
    }

}
