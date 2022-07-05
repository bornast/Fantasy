package hr.bornast.fantasy.application.query.role;

import hr.bornast.fantasy.application.query.common.PaginationQuery;
import lombok.Data;

@Data
public class RoleQuery extends PaginationQuery {
    private String name;
}
