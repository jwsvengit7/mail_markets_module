package com.jwsven.mail_markets.services.impl;


import com.jwsven.mail_markets.domain.Mail;
import com.jwsven.mail_markets.event_driven.listeners.MailRequest;
import com.jwsven.mail_markets.services.MailService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class MailServiceImpl implements MailService {
    private final HttpHeaders httpHeaders;
    private final ApplicationEventPublisher publisher;

    public MailServiceImpl(
            HttpHeaders httpHeaders,
            ApplicationEventPublisher publisher)
    {

        this.httpHeaders=httpHeaders;
        this.publisher=publisher;
    }

    @Override
    public <T extends Mail,K> T sendMail(T data,K url) {
        publisher.publishEvent(new MailRequest<>(data,url));
        return data;
    }

    @Override
    public <T> T acceptKey(T breveApiKey) {
        httpHeaders.set("Authorization", "Bearer " + breveApiKey);
        httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set("api-key", (String) breveApiKey);
        return breveApiKey;
    }

    @Override
    public <K,T> T castMessage(K data) {
        T result = null;
        return result;
    }


}
