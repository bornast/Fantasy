package hr.bornast.fantasy.application.query.match;

import hr.bornast.fantasy.application.query.common.PaginationQuery;
import lombok.Data;

@Data
public class MatchQuery extends PaginationQuery {
    private String teamName;
}