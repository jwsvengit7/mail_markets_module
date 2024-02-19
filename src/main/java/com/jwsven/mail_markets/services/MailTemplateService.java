package com.jwsven.mail_markets.services;

import com.jwsven.mail_markets.domain.Mail;
import com.jwsven.mail_markets.helpers.MailReducers;

public interface MailTemplateService extends MailReducers<Mail,String> {
}
