package com.jwsven.mail_markets.event_driven.listeners;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class MailRequest<T,K> extends ApplicationEvent {
    private K url;
    private T data;

    public MailRequest(T data,K url) {
        super(data);
        this.data=data;
        this.url=url;
    }
}
