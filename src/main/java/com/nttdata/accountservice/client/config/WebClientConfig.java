package com.nttdata.accountservice.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@Configuration
public class WebClientConfig {

    @Bean( "webClient" )
    public WebClient webClient() {
        return WebClient.create();
    }
}