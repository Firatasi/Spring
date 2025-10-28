package com.firat.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private MessageType messageType;

    private String ofStatic;

    public String prepareErrorMessage(){
        StringBuilder builder = new StringBuilder();

        builder.append(this.messageType.getMessage());
        if (ofStatic != null){
            builder.append(" : " + ofStatic);
        }

        return builder.toString();
    }

}
