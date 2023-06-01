package com.nttdata.accountservice.exception;

import com.nttdata.accountservice.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler( RuntimeException.class )
    public ResponseEntity<Response> handleException( RuntimeException e ) {
        Response apiError = new Response();
        apiError.setStatusCode( HttpStatus.BAD_REQUEST.value() );
        apiError.setHttpStatus( HttpStatus.BAD_REQUEST );
        apiError.setErrors( List.of( e.getMessage() ) );

        return new ResponseEntity<>( apiError, HttpStatus.BAD_REQUEST );
    }

}
