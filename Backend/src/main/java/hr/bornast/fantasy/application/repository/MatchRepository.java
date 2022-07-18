package hr.bornast.fantasy.application.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.domain.model.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MatchRepository {
    Page<Match> findAll(Pageable paging);
    Page<Match> findByName(String name, Pageable paging);
    Page<Match> findByTeamId(int teamId, Pageable paging);
    List<Match> findByTeamId(int teamId, int leagueId);
    List<Match> findAll();
    Optional<Match> findById(int id);
    List<Match> findByIds(List<Integer> ids);
    Match create(Match match);
    Match update(Match match);
    void delete(int id);

}
