package com.demo.ulke_baskent.exception;
//kendi hata sınıfımız
public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
