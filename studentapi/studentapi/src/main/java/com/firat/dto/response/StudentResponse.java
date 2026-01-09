package com.firat.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
@Getter
@Builder
public class StudentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Instant createdAt;
}
