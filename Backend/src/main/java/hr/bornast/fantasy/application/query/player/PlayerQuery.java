package hr.bornast.fantasy.application.query.player;

import hr.bornast.fantasy.application.query.common.PaginationQuery;
import lombok.Data;

@Data
public class PlayerQuery extends PaginationQuery {
    private String name;
}