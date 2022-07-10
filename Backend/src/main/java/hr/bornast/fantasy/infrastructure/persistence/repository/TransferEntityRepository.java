package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.infrastructure.persistence.entity.TransferEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferEntityRepository extends JpaRepository<TransferEntity, Integer> {
    @Query("SELECT t from TransferEntity t where lower(t.player.name) like lower(concat('%',?1,'%'))")
    Page<TransferEntity> findByNameContainingIgnoreCase(String playerName, Pageable pageable);
    List<TransferEntity> findByIdIn(List<Integer> ids);
    @Query(value = "select * from transfers where player_id = ?1 order by transfer_date desc limit 1;", nativeQuery = true)
    Optional<TransferEntity> findLastPlayerTransfer(int playerId);
}
