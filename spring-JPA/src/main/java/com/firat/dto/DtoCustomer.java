package com.firat.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) //bu classın içinde null bir şey kaldıysa nulları dönme
public class DtoCustomer {

    private Long id;
    private String name;
    private DtoAdress adress;

}
