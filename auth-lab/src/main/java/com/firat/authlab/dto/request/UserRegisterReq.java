package com.firat.authlab.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterReq {

    @NotBlank(message = "Kullanıcı adı boş bırakılamaz! ")
    @Size(message = "Kullanıcı adı uzunluğu 3 ile 25 arasında olmalıdır! ", min = 3, max = 25)
    private String username;

    @NotBlank(message = "E-mail boş bırakılamaz! ")
    @Email(message = "Lütfen geçerli bir mail adresi giriniz! ")
    private String email;

    @NotBlank(message = "Şifre boş bırakılamaz! ")
    @Size(message = "Şifre en az 6 karakterden oluşmalıdır", min = 6)
    private String password;
}
