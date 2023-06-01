package com.nttdata.accountservice.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public final class ResponseUtil {

    private ResponseUtil() {
    }

    public static Response getOkResponse( Map<?, ?> data ) {
        Response response = new Response();
        response.setStatusCode( HttpStatus.OK.value() );
        response.setHttpStatus( HttpStatus.OK );
        response.setData( data );

        return response;
    }

    public static Response getCreatedResponse( Map<?, ?> data ) {
        Response response = new Response();
        response.setStatusCode( HttpStatus.CREATED.value() );
        response.setHttpStatus( HttpStatus.CREATED );
        response.setData( data );

        return response;
    }

    public static Response getBadResponse( List<String> errors ) {
        Response response = new Response();
        response.setStatusCode( HttpStatus.BAD_REQUEST.value() );
        response.setHttpStatus( HttpStatus.BAD_REQUEST );
        response.setErrors( errors );

        return response;
    }

    public static ResponseEntity<Response> getRestResponse( Response response ) {
        ResponseEntity<Response> responseEntity = ResponseEntity.ok( response );

        if ( HttpStatus.CREATED.equals( response.getHttpStatus() ) ) {
            responseEntity = new ResponseEntity<>( response, HttpStatus.CREATED );
        }

        if ( Objects.nonNull( response.getErrors() ) ) {
            responseEntity = ResponseEntity.badRequest().body( response );
        }


        return responseEntity;
    }
}
