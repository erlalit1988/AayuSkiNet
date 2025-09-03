package com.aayuskinet.api.errors;

import com.aayuskinet.api.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(NotFoundException ex, WebRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                // In production, you might not want to expose stack trace
                // For development, you can include it:
                // request.getDescription(false) + "\n" + ex.getStackTrace()[0].toString()
                "Internal server error"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // You can add more specific exception handlers here
    // @ExceptionHandler(YourCustomException.class)
    // public ResponseEntity<ApiErrorResponse> handleYourCustomException(YourCustomException ex, WebRequest request) {
    //     ApiErrorResponse errorResponse = new ApiErrorResponse(
    //             HttpStatus.BAD_REQUEST.value(),
    //             ex.getMessage(),
    //             request.getDescription(false)
    //     );
    //     return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    // }
}
