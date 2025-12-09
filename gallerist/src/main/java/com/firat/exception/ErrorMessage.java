package com.firat.exception;

import com.firat.model.Address;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private String ofStatic;

    private MessageType messageType;

    public ErrorMessage(MessageType messageType, @NotNull Address adressId) {
    }

    public String prepareErrorMessage() {
        StringBuilder builder = new StringBuilder(); //strigleri birleştirmek için kullanılan bir sınıf

        builder.append(messageType.getMessage());
        if (this.ofStatic != null) {
            builder.append(" : " + ofStatic);
        }

        return builder.toString();

    }
}
