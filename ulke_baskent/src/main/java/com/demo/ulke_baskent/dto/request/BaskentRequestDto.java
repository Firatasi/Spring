package com.demo.ulke_baskent.dto.request;

import com.demo.ulke_baskent.anotasyon.NotAdmin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaskentRequestDto {
    @NotBlank(message = "İsim kısmı boş bırakılamaz! ")
    @NotAdmin
    private String name;
}
