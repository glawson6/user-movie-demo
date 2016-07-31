package com.taptech.ttis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


//@SpringBootApplication
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
@ComponentScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    public static class SimpleConfiguration{

        @Value("${agora.ns.show-json-pretty:false}")
        boolean showJSONPretty;

        @Bean
        @Qualifier("showJSONPretty")
        boolean showJSONPretty(){
            return showJSONPretty;
        }
    }


}
