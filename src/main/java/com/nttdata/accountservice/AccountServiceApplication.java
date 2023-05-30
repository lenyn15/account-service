package com.nttdata.accountservice;

import com.nttdata.accountservice.client.feign.CustomerClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import reactor.core.publisher.Signal;

@Slf4j
@EnableFeignClients
@SpringBootApplication
public class AccountServiceApplication implements CommandLineRunner {

    @Autowired
    private CustomerClient customerClient;

    public static void main( String[] args ) {
        SpringApplication.run( AccountServiceApplication.class, args );
    }

    @Override
    public void run( String... args ) throws Exception {
        log.info( "EXECUTING......" );
        customerClient.getCustomerInformation( "74224460" )
                .doOnEach( Signal::get )
                .doOnNext( responseResponseEntity -> log.info( "response: " + responseResponseEntity.getBody() ) )
                .subscribe();
    }
}
