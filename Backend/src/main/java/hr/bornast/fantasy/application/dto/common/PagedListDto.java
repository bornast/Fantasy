package hr.bornast.fantasy.application.dto.common;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedListDto<T> {
    private List<T> result;
    private PaginationDto pagination;

    public PagedListDto<T> getPagedResult(Page<T> page) {
        return new PagedListDto<>(page.stream().toList(), new PaginationDto<>(page));
    }
}
