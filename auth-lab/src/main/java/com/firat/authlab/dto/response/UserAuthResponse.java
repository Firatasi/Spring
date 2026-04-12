package com.firat.authlab.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthResponse {
    String token;
    String refreshToken;
    String type;
    String username;
    String role;

    public UserAuthResponse(String token, String bearer, String username, String name) {
    }
}
