package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.application.repository.FormationRepository;
import hr.bornast.fantasy.domain.model.Formation;
import hr.bornast.fantasy.infrastructure.persistence.entity.FormationEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.FormationEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FormationRepositoryImpl implements FormationRepository {

    private final FormationEntityRepository formationRepository;
    private final ModelMapper mapper;

    @Override
    public Page<Formation> findAll(Pageable paging) {
        return formationRepository.findAll(paging).map(x -> mapper.map(x, Formation.class));
    }

    @Override
    public Page<Formation> findByName(String name, Pageable pageable) {
        return formationRepository.findByNameContainingIgnoreCase(name, pageable)
            .map(x -> mapper.map(x, Formation.class));
    }

    @Override
    public List<Formation> findAll() {
        return formationRepository.findAll().stream().map(x -> mapper.map(x, Formation.class)).toList();
    }

    @Override
    public Optional<Formation> findById(int id) {
        return formationRepository.findById(id).map(x -> mapper.map(x, Formation.class));
    }

    @Override
    public Optional<Formation> findByName(String name) {
        return formationRepository.findByName(name)
            .map(x -> mapper.map(x, Formation.class));
    }

    @Override
    public Formation create(Formation formation) {
        return mapper.map(formationRepository.save(mapper.map(formation, FormationEntity.class)), Formation.class);
    }

    @Override
    public void delete(int id) {
        formationRepository.deleteById(id);
    }

    @Override
    public Formation update(Formation formation) {
        return mapper.map(formationRepository.save(mapper.map(formation, FormationEntity.class)), Formation.class);
    }

}
