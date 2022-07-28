package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.Optional;

import hr.bornast.fantasy.application.repository.RateRepository;
import hr.bornast.fantasy.domain.model.MatchPlayerRate;
import hr.bornast.fantasy.infrastructure.persistence.entity.MatchPlayerRateEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.MatchPlayerRateEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MatchPlayerRateRepositoryImpl implements RateRepository {

    private final MatchPlayerRateEntityRepository rateRepository;
    private final ModelMapper mapper;

    @Override
    public Optional<MatchPlayerRate> findById(int id) {
        return rateRepository.findById(id).map(x -> mapper.map(x, MatchPlayerRate.class));
    }

    @Override
    public Optional<MatchPlayerRate> findByMatchIdAndPlayerIdAndUserId(int matchId, int playerId, int userId) {
        return rateRepository.findByMatchIdAndPlayerIdAndUserId(matchId, playerId, userId)
            .map(x -> mapper.map(x, MatchPlayerRate.class));
    }

    @Override
    public double findPlayerMatchRate(int playerId, int matchId) {
        return rateRepository.findPlayerMatchRate(playerId, matchId);
    }

    @Override
    public double findPlayerRate(int playerId) {
        return rateRepository.findPlayerRate(playerId);
    }

    @Override
    public MatchPlayerRate create(MatchPlayerRate rate) {
        return mapper.map(rateRepository.saveAndFlush(mapper.map(rate, MatchPlayerRateEntity.class)), MatchPlayerRate.class);
    }

    @Override
    public void delete(int id) {
        rateRepository.deleteById(id);
    }

    @Override
    public MatchPlayerRate update(MatchPlayerRate rate) {
        return mapper.map(rateRepository.save(mapper.map(rate, MatchPlayerRateEntity.class)), MatchPlayerRate.class);
    }

}
