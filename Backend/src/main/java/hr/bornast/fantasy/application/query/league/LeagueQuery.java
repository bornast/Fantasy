package hr.bornast.fantasy.application.query.league;

import hr.bornast.fantasy.application.query.common.PaginationQuery;
import lombok.Data;

@Data
public class LeagueQuery extends PaginationQuery {
    private String name;
}