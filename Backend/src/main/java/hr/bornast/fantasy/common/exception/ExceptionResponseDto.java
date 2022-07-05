package hr.bornast.fantasy.common.exception;

import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExceptionResponseDto {
    private int statusCode;
    private Map<String, Object> errors = new HashMap<>();
}
