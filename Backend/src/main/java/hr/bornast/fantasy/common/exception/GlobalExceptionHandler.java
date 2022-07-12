package hr.bornast.fantasy.common.exception;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponseDto> handle(Exception ex) {
        ex.printStackTrace();

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        HashMap<String, Object> errors = new HashMap<>();

        if (ex instanceof EntityNotFoundException) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        else if (ex instanceof AccessDeniedException) {
            httpStatus = HttpStatus.FORBIDDEN;
        }
        else if (ex instanceof InternalAuthenticationServiceException) {
            httpStatus = HttpStatus.UNAUTHORIZED;
        }
        else if (ex instanceof BadCredentialsException) {
            httpStatus = HttpStatus.UNAUTHORIZED;
        }
        else if (ex instanceof ValidationException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            errors.putAll(((ValidationException) ex).getErrors());
        }
        else if (ex instanceof MethodArgumentNotValidException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            BindingResult result = ((MethodArgumentNotValidException) ex).getBindingResult();
            var fieldErrors = result.getFieldErrors();
            for (var fieldError: fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        }

        var response = ExceptionResponseDto.builder()
            .statusCode(httpStatus.value())
            .errors(errors)
            .build();

        return ResponseEntity.status(httpStatus).body(response);
    }

}
