package com.uzay.game.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameRequest {
    @Size(min = 1, max = 50)
    @NotBlank(message = "isim boş olamaz")
    private String name;

    @Size(min = 1, max = 50)
    @NotBlank(message = "kategori boş olamaz")
    private String category;


    @NotBlank(message = "fiyat boş olamaz")
    private Integer price;
}
