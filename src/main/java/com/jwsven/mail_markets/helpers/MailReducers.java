package com.jwsven.mail_markets.helpers;

public interface MailReducers<T,K> {
     <T> T sendMail(T data,K url);
     <T> T acceptKey(T bearer);
     <T> T castMessage(String data);

}
