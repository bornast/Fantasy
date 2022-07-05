package hr.bornast.fantasy.application.query.user;

import hr.bornast.fantasy.application.query.common.PaginationQuery;
import lombok.Data;

@Data
public class UserQuery extends PaginationQuery {
    private String username;
}