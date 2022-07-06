package hr.bornast.fantasy.application.query.formation;

import hr.bornast.fantasy.application.query.common.PaginationQuery;
import lombok.Data;

@Data
public class FormationQuery extends PaginationQuery {
    private String name;
}
