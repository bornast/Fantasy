package hr.bornast.fantasy.application.query.season;

import hr.bornast.fantasy.application.query.common.PaginationQuery;
import lombok.Data;

@Data
public class SeasonQuery extends PaginationQuery {
    private String name;
}