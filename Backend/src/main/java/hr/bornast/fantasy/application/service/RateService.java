package hr.bornast.fantasy.application.service;

import hr.bornast.fantasy.application.command.rate.SaveRateCommand;
import hr.bornast.fantasy.application.command.rate.UpdateRateCommand;
import hr.bornast.fantasy.application.dto.rate.RateDto;
import hr.bornast.fantasy.application.query.rate.RateQuery;

public interface RateService {
    double findPlayerRate(int playerId);
    double findPlayerMatchRate(RateQuery query);
    RateDto findOne(RateQuery query);
    RateDto create(SaveRateCommand command);
    RateDto update(int id, UpdateRateCommand command);
    void delete(int id);
}
