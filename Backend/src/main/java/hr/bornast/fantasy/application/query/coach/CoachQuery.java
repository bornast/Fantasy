package hr.bornast.fantasy.application.query.coach;

import hr.bornast.fantasy.application.query.common.PaginationQuery;
import lombok.Data;

@Data
public class CoachQuery extends PaginationQuery {
    private String name;
}