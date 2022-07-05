package hr.bornast.fantasy.application.dto.common;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class PaginationDto<T> {
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private int totalCount;

    public PaginationDto(Page<T> page) {
        setPageSize(page.getSize());
        setCurrentPage(page.getNumber());
        setTotalPages(page.getTotalPages());
        setTotalCount((int)page.getTotalElements());
    }
}
