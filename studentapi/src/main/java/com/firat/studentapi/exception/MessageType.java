package com.firat.studentapi.exception;

import lombok.Getter;

@Getter
public enum MessageType {
    NO_RECORD_EXIST("1004" , "kayıt bulunamadı"),
    TOKEN_IS_EXPIRED("1005" , "tokenın süresi bitmiştir"),
    USERNAME_NOT_FOUND("1006" , "username bulunamadı"),
    USERNAME_OR_PASSWORD_INVALID("1007" , "kullanıcı adı veya şifre hatalı"),
    REFRESH_TOKEN_NOT_FOUND("1008" , "refresh token bulunamadı"),
    REFRESH_TOKEN_IS_EXPIRED("1009" , "refresh tokenın süresi bitmiştir"),
    GENERAL_EXCEPTION("9999" , "genel bir hata oluştu"), REFRESH_TOKEN_INVALID("1013", "token yanlış" );

    private String code;
    private String message;
    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
