package hr.bornast.fantasy.application.query.position;

import hr.bornast.fantasy.application.query.common.PaginationQuery;
import lombok.Data;

@Data
public class PositionQuery extends PaginationQuery {
    private String name;
}
