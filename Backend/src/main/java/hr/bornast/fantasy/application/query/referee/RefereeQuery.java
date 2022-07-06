package hr.bornast.fantasy.application.query.referee;

import hr.bornast.fantasy.application.query.common.PaginationQuery;
import lombok.Data;

@Data
public class RefereeQuery extends PaginationQuery {
    private String name;
}