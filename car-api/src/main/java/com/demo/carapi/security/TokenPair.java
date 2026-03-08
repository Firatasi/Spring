package com.demo.carapi.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//dto
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenPair {

    private String accessToken;
    private String refreshToken;

}
