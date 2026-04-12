package com.firat.dto;

import lombok.Data;

@Data
public class DtoUser extends DtoBase {

    private String username;

    private String password;
}
