package pl.inpost.shopping.adapter.in;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.inpost.shopping.domain.exception.DomainException;
import pl.inpost.shopping.domain.exception.ProductNotFound;

@ControllerAdvice
public class CalculatePriceDiscountAdvice {

    @ExceptionHandler({ProductNotFound.class})
    protected ResponseEntity<ErrorResponse> handle(final ProductNotFound ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(createErrorResponse(ex));
    }

    @ExceptionHandler({
            DomainException.class,
            ConstraintViolationException.class,
    })
    protected ResponseEntity<ErrorResponse> handle(final RuntimeException ex) {
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(createErrorResponse(ex));
    }

    private static ErrorResponse createErrorResponse(RuntimeException ex) {
        return new ErrorResponse(
                "An error occurred during processing of your request",
                ex.getClass().getSimpleName(),
                ex.getMessage()
        );
    }

    record ErrorResponse(String error, String cause, String exceptionMessage) {
    }
}

