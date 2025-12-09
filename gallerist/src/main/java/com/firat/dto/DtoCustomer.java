package com.firat.dto;

import com.firat.model.Account;
import com.firat.model.Address;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
@Data
public class DtoCustomer extends DtoBase{

    private String firstName;

    private String lastName;

    private String tckn;

    private Date dateOfBirth;

    private DtoAddress adressId;

    private DtoAccount accountId;

}
