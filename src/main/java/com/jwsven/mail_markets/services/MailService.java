package com.jwsven.mail_markets.services;


import com.jwsven.mail_markets.helpers.MailReducers;

public interface MailService  {

    <T,K> T  sendMail(T data,K url);

    <T> T acceptKey(T bearer);
    <K,T> T castMessage(K data);

}
