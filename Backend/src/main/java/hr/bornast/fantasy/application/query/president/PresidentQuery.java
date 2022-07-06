package hr.bornast.fantasy.application.query.president;

import hr.bornast.fantasy.application.query.common.PaginationQuery;
import lombok.Data;

@Data
public class PresidentQuery extends PaginationQuery {
    private String name;
}