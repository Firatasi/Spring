package com.firat.authlab.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginReq {
    @NotBlank(message = "Kullanıcı adı boş olamaz!")
    String username;

    @NotBlank(message = "Şifre boş olamaz!")
    String password;
}
