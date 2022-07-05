package hr.bornast.fantasy.application.query.common;

import lombok.Data;

@Data
public class PaginationQuery {
    private int pageSize = 5;
    private int pageNumber = 0;
}
