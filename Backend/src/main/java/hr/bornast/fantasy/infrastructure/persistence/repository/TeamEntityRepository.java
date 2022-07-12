package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.infrastructure.persistence.entity.TeamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamEntityRepository extends JpaRepository<TeamEntity, Integer> {
    Optional<TeamEntity> findByName(String name);
    Page<TeamEntity> findByNameContainingIgnoreCase(String name, Pageable paging);
    List<TeamEntity> findByIdIn(List<Integer> ids);

    @Query(value = "select t.* from teams t inner join favourite_teams ft on (t.id = ft.team_id) where ft.user_id = ?1", nativeQuery = true)
    Page<TeamEntity> findFavouriteTeams(int userId, Pageable paging);

    @Query(value = "select t.* from teams t inner join favourite_teams ft on (t.id = ft.team_id) where ft.user_id = ?1 and lower(t.name) like lower(concat('%',?2,'%'))", nativeQuery = true)
    Page<TeamEntity> findFavouriteTeams(int userId, String name, Pageable paging);

    @Query(value = "select t.* from teams t inner join favourite_teams ft on (t.id = ft.team_id) where ft.user_id = ?1", nativeQuery = true)
    List<TeamEntity> findFavouriteTeams(int userId);

    @Query(value = "select t.* from teams t inner join favourite_teams ft on (t.id = ft.team_id) where ft.user_id = ?1 and lower(t.name) like lower(concat('%',?2,'%'))", nativeQuery = true)
    List<TeamEntity> findFavouriteTeams(int userId, String name);

    @Query(value = "select t.* from teams t where lower(t.name) like lower(concat('%',?1,'%'))", nativeQuery = true)
    List<TeamEntity> findAll(String name);

}
