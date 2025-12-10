package com.firat.exception;

import lombok.Getter;

@Getter
public enum MessageType {
    GENERAL_EXCEPTION("9999", "Genel bir hata oluştu! "),
    NO_RECORD_EXIST("1004", "Kayıt bulunamadı! "),
    TOKEN_IS_EXPIRED("1005", "Token süresi bitmiş!"),
    USERNAME_NOT_FOUND("1006","Username bulunamadi!"),
    USERNAME_OR_PASSWORD_INVALID("1007","kullanıcı adı veya şifre hatalı!"),
    REFRESH_TOKEN_INVALID("1008","Refresh token bulunamadi!"),
    CURRENY_RATES_IS_OCCURED("1010" , "döviz kuru alınamadı"),
    REFRESH_TOKEN_IS_EXPIRED("1009","Refresh token süresi doldu!");


    private String code;
    private String message;
     MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
