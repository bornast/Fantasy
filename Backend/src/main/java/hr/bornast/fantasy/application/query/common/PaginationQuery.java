package hr.bornast.fantasy.application.query.common;

import lombok.Data;

@Data
public class PaginationQuery {
    private int size = 5;
    private int page = 0;
}
