package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.application.repository.TransferRepository;
import hr.bornast.fantasy.domain.model.Transfer;
import hr.bornast.fantasy.infrastructure.persistence.entity.TransferEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.TransferEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransferRepositoryImpl implements TransferRepository {

    private final TransferEntityRepository transferRepository;
    private final ModelMapper mapper;

    @Override
    public Page<Transfer> findAll(Pageable paging) {
        return transferRepository.findAll(paging).map(x -> mapper.map(x, Transfer.class));
    }

    @Override
    public Page<Transfer> findByName(String name, Pageable pageable) {
        return transferRepository.findByNameContainingIgnoreCase(name, pageable)
            .map(x -> mapper.map(x, Transfer.class));
    }

    @Override
    public List<Transfer> findAll() {
        return transferRepository.findAll().stream().map(x -> mapper.map(x, Transfer.class)).toList();
    }

    @Override
    public Optional<Transfer> findById(int id) {
        return transferRepository.findById(id).map(x -> mapper.map(x, Transfer.class));
    }

    @Override
    public List<Transfer> findByIds(List<Integer> ids) {
        return transferRepository.findByIdIn(ids).stream().map(x -> mapper.map(x, Transfer.class)).toList();
    }

    @Override
    public Transfer create(Transfer transfer) {
        return mapper.map(transferRepository.save(mapper.map(transfer, TransferEntity.class)), Transfer.class);
    }

    @Override
    public void delete(int id) {
        transferRepository.deleteById(id);
    }

    @Override
    public Transfer update(Transfer transfer) {
        return mapper.map(transferRepository.save(mapper.map(transfer, TransferEntity.class)), Transfer.class);
    }

}
