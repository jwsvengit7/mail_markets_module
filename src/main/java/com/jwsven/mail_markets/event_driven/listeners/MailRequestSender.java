package com.jwsven.mail_markets.event_driven.listeners;


import com.jwsven.mail_markets.domain.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class MailRequestSender implements ApplicationListener<MailRequest<Mail,String>> {
    @Qualifier("restTemplateForMail")
    private final RestTemplate restTemplateForMail;
    @Qualifier("restTemplateForApp")
    private final RestTemplate restTemplateApp;

    public MailRequestSender(
            @Qualifier("restTemplateForMail") RestTemplate restTemplateForMail,
            @Qualifier("restTemplateForApp") RestTemplate restTemplateApp) {
        this.restTemplateForMail = restTemplateForMail;
        this.restTemplateApp = restTemplateApp;
    }

    @Override
    public void onApplicationEvent(MailRequest<Mail,String> event) {
        Mail  request = event.getData();
        String url = event.getUrl();
        log.info("request {} ",request);
        log.info("url {} ",url);
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
