package hr.bornast.fantasy.infrastructure.persistence.mapper;

import hr.bornast.fantasy.domain.model.Match;
import hr.bornast.fantasy.infrastructure.persistence.entity.MatchEntity;

public interface MatchEntityMapper {
    Match map(MatchEntity matchEntity);
    MatchEntity map(Match match);
}
