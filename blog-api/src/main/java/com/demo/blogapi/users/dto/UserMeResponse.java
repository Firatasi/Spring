package com.demo.blogapi.users.dto;

import com.demo.blogapi.users.Role;

public record UserMeResponse(Long id, String email, String fullName, Role role) {}

