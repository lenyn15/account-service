package com.nttdata.accountservice.util;

import com.nttdata.accountservice.exception.RequestException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public final class ErrorUtil {

    private ErrorUtil() {
    }

    public static Mono<Throwable> getErrorsFromApi( ClientResponse response ) {
        return response.bodyToMono( Response.class )
                .flatMap( errorResponse -> {
                    String errorMessage = errorResponse.getErrors().get( 0 );
                    return Mono.error( new RequestException( errorMessage ) );
                } );
    }

    /**
     * Cast errors from fields validation
     *
     * @param throwable Class exception with message errors
     * @return Mono<List<String>>
     */
    public static Mono<List<String>> getErrorMessages( Throwable throwable ) {
        if ( throwable instanceof RequestException ) {
            return Mono.just( List.of( throwable.getMessage() ) );
        }

        if ( throwable instanceof WebExchangeBindException ) {
            WebExchangeBindException bindException = ( WebExchangeBindException ) throwable;
            return Flux.fromIterable( bindException.getFieldErrors() )
                    .map( DefaultMessageSourceResolvable::getDefaultMessage )
                    .collectList();
        }

        return Mono.error( throwable );
    }
}
