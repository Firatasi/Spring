package com.firat.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXIST("1001", "KAYIT BULUNAMADI! "),
    GENERAL_EXCEPTİON("99999","GENEL BİR HATA OLUŞTU! ");

    private String code;
    private String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }



}
