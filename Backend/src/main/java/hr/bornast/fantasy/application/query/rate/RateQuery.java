package hr.bornast.fantasy.application.query.rate;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RateQuery {
    @NotNull
    private int matchId;
    @NotNull
    private int playerId;
}