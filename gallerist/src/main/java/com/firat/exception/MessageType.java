package com.firat.exception;

import lombok.Getter;

@Getter
public enum MessageType {
    GENERAL_EXCEPTION("9999", "Genel bir hata oluştu! "),
    TOKEN_IS_EXPIRED("1005", "Token has expired!"),
    NO_RECORD_EXIST("1004", "Kayıt bulunamadı! ");

    private String code;
    private String message;
     MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
