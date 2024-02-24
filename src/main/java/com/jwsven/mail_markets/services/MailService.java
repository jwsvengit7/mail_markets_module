package com.jwsven.mail_markets.services;


import com.jwsven.mail_markets.domain.Mail;

public interface MailService  {

    <T extends Mail,K> T sendMail(T data, K url);
    <T> T acceptKey(T bearer);
    <K,T> T castMessage(K data);

}
