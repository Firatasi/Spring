package com.firat.dto;

import com.firat.model.Account;
import com.firat.model.Address;
import com.firat.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCustomerIU extends BaseEntity {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String tckn;

    @NotNull
    private Date dateOfBirth;

    @NotNull
    private Address adressId;

    @NotNull
    private Account accountId;



}
