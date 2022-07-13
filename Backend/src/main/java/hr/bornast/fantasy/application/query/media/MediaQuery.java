package hr.bornast.fantasy.application.query.media;

import hr.bornast.fantasy.application.query.common.PaginationQuery;
import lombok.Data;

@Data
public class MediaQuery extends PaginationQuery {
    private int matchId;
}