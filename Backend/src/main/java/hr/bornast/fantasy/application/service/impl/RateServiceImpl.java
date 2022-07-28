package hr.bornast.fantasy.application.service.impl;

import hr.bornast.fantasy.application.command.rate.SaveRateCommand;
import hr.bornast.fantasy.application.command.rate.UpdateRateCommand;
import hr.bornast.fantasy.application.dto.rate.RateDto;
import hr.bornast.fantasy.application.query.rate.RateQuery;
import hr.bornast.fantasy.application.repository.MatchRepository;
import hr.bornast.fantasy.application.repository.PlayerRepository;
import hr.bornast.fantasy.application.repository.RateRepository;
import hr.bornast.fantasy.application.repository.UserRepository;
import hr.bornast.fantasy.application.service.RateService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.domain.model.MatchPlayerRate;
import hr.bornast.fantasy.domain.model.RecordName;
import hr.bornast.fantasy.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository;
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private final ModelMapper mapper;

    @Override
    public double findPlayerRate(int playerId) {
        return rateRepository.findPlayerRate(playerId);
    }

    @Override
    public double findPlayerMatchRate(RateQuery query) {
        return rateRepository.findPlayerMatchRate(query.getPlayerId(), query.getMatchId());
    }

    @Override
    public RateDto findOne(RateQuery query) {
        return rateRepository.findByMatchIdAndPlayerIdAndUserId(query.getMatchId(),
                query.getPlayerId(),
                getCurrentUser().getId())
            .map(x -> mapper.map(x, RateDto.class))
            .orElse(new RateDto());
    }

    public RateDto create(SaveRateCommand command) {
        var rate = mapper.map(command, MatchPlayerRate.class);
        setRelatedData(command, rate);
        return mapper.map(rateRepository.create(rate), RateDto.class);
    }

    @Override
    public RateDto update(int id, UpdateRateCommand command) {
        var rate = rateRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);

        if (rate.getUser().getId() != getCurrentUser().getId()) {
            throw new AccessDeniedException("Access Denied");
        }

        rate.setRate(command.getRate());

        return mapper.map(rateRepository.update(rate), RateDto.class);
    }

    @Override
    public void delete(int id) {
        rateRepository.delete(id);
    }

    private User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
            .orElseThrow(EntityNotFoundException::new);
    }

    private void setRelatedData(SaveRateCommand command, MatchPlayerRate rate) {
        var player = playerRepository.findById(command.getPlayerId())
            .orElseThrow(EntityNotFoundException::new);
        rate.setPlayer(player);

        var match = matchRepository.findById(command.getMatchId())
            .orElseThrow(EntityNotFoundException::new);
        rate.setMatch(mapper.map(match, RecordName.class));

        rate.setUser(getCurrentUser());
    }

}
