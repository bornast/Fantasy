package hr.bornast.fantasy.application.query.transfer;

import hr.bornast.fantasy.application.query.common.PaginationQuery;
import lombok.Data;

@Data
public class TransferQuery extends PaginationQuery {
    private String playerName;
}