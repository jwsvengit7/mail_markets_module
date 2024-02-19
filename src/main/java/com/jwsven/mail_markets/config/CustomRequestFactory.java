package com.jwsven.mail_markets.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
//import java.util.logging.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class CustomRequestFactory implements ClientHttpRequestFactory {

    private final SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
    private final String baseUrl;
    private final HttpHeaders headers;
    private final Logger logger = LoggerFactory.getLogger(CustomRequestFactory.class);

    public CustomRequestFactory(String baseUrl, HttpHeaders headers) {
        Assert.hasText(baseUrl, "Base URL must not be empty");
        logger.info("status: ",baseUrl, "Base URL must not be empty");
        this.baseUrl = baseUrl;
        this.headers = headers;
    }

    @Override
    public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
        ClientHttpRequest request = null;
        try {
            request = requestFactory.createRequest(new URI(baseUrl + uri.toString()), httpMethod);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        headers.forEach(request.getHeaders()::put);
        logger.info("headers: {}" , headers);
        return request;
    }
}
