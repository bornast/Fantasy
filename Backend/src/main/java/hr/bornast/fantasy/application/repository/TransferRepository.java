package hr.bornast.fantasy.application.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.domain.model.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransferRepository {
    Page<Transfer> findAll(Pageable paging);
    Page<Transfer> findByName(String name, Pageable paging);
    Page<Transfer> findByTeamId(int teamId, Pageable paging);
    List<Transfer> findAll();
    Optional<Transfer> findLastPlayerTransfer(int playerId);
    Optional<Transfer> findById(int id);
    List<Transfer> findByIds(List<Integer> ids);
    Transfer create(Transfer transfer);
    Transfer update(Transfer transfer);
    void delete(int id);

}
