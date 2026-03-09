package com.firat.authlab.dto.response;

import com.firat.authlab.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String username;
    private String email;
    private Role role;

    public UserResponse(Long id, String username, String email, String name) {
    }
}
