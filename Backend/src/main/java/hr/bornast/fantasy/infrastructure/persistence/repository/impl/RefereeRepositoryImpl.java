package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.application.repository.RefereeRepository;
import hr.bornast.fantasy.domain.model.Referee;
import hr.bornast.fantasy.infrastructure.persistence.entity.RefereeEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.RefereeEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefereeRepositoryImpl implements RefereeRepository {

    private final RefereeEntityRepository refereeRepository;
    private final ModelMapper mapper;

    @Override
    public Page<Referee> findAll(Pageable paging) {
        return refereeRepository.findAll(paging).map(x -> mapper.map(x, Referee.class));
    }

    @Override
    public Page<Referee> findByName(String name, Pageable pageable) {
        return refereeRepository.findByNameContainingIgnoreCase(name, pageable)
            .map(x -> mapper.map(x, Referee.class));
    }

    @Override
    public List<Referee> findAll() {
        return refereeRepository.findAll().stream().map(x -> mapper.map(x, Referee.class)).toList();
    }

    @Override
    public Optional<Referee> findById(int id) {
        return refereeRepository.findById(id).map(x -> mapper.map(x, Referee.class));
    }

    @Override
    public Optional<Referee> findByName(String name) {
        return refereeRepository.findByName(name)
            .map(x -> mapper.map(x, Referee.class));
    }

    @Override
    public Referee create(Referee referee) {
        return mapper.map(refereeRepository.save(mapper.map(referee, RefereeEntity.class)), Referee.class);
    }

    @Override
    public void delete(int id) {
        refereeRepository.deleteById(id);
    }

    @Override
    public Referee update(Referee referee) {
        return mapper.map(refereeRepository.save(mapper.map(referee, RefereeEntity.class)), Referee.class);
    }

}
