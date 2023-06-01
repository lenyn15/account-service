package com.nttdata.accountservice.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@Getter
@Setter
@JsonInclude( Include.NON_NULL )
@NoArgsConstructor
public class Response {
    private int statusCode;
    private HttpStatus httpStatus;
    private Map<?, ?> data;
    private List<String> errors;
}
