package hr.bornast.fantasy.application.repository;

import java.util.Optional;

import hr.bornast.fantasy.domain.model.MatchPlayerRate;

public interface RateRepository {
    Optional<MatchPlayerRate> findById(int id);
    Optional<MatchPlayerRate> findByMatchIdAndPlayerIdAndUserId(int matchId, int playerId, int userId);
    double findPlayerMatchRate(int playerId, int matchId);
    double findPlayerRate(int playerId);
    MatchPlayerRate create(MatchPlayerRate rate);
    MatchPlayerRate update(MatchPlayerRate rate);
    void delete(int id);
}
