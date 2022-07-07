package hr.bornast.fantasy.application.query.team;

import hr.bornast.fantasy.application.query.common.PaginationQuery;
import lombok.Data;

@Data
public class TeamQuery extends PaginationQuery {
    private String name;
}