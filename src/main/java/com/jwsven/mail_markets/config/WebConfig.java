package com.jwsven.mail_markets.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplateHandler;

import java.net.URI;

@Configuration
@Slf4j
public class WebConfig {

    @Value("${brevo.api.key}")
    private String brevoApiKey;

    @Value("${brevo.mail.url}")
    private String mailUrl;

    @Bean
    public RestTemplate restTemplateForMail() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = httpHeadersForMail();
        log.info("===================== {} ",headers,"  =====================");
        return restTemplate;
    }

    @Bean
    public RestTemplate restTemplateForApp() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = httpHeadersForApp();
        log.info("===================== {} ",headers,"  =====================");

        return restTemplate;
    }

    @Bean
    public HttpHeaders httpHeadersForMail(){
       return new HttpHeaders();
    }

    private HttpHeaders httpHeadersForApp(){
        HttpHeaders headers = new HttpHeaders();
        headers.setOrigin(mailUrl);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("api-key", brevoApiKey);
        return headers;
    }
}
