package com.buythebook.bookstore.userservice.errorhandler;

import com.buythebook.bookstore.userservice.dto.response.RestErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
    /*
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String message = fieldErrors != null && fieldErrors.size() > 0 ?fieldErrors.get(0).getDefaultMessage() : "Method Argument Not Valid";
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse("Method argument not valid", message));

    }*/


    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorResponse> handleException(Exception e) {
        RestErrorResponse errorResponse = new RestErrorResponse("500", e.getMessage());
        logger.error("Error on service" + e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}