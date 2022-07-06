package hr.bornast.fantasy.application.query.stadium;

import hr.bornast.fantasy.application.query.common.PaginationQuery;
import lombok.Data;

@Data
public class StadiumQuery extends PaginationQuery {
    private String name;
}
