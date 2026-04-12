package com.firat.model;

import com.firat.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {
    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "iban")
    private String iban;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency_type")
    @Enumerated(EnumType.STRING)//ne verirsem dolar ve tl veri tabanına yansır
    private CurrencyType currencyType; //enum = Sınırlı sayıda değişmeyen sabitleri tip güvenli tanımlayan özel sınıf.
}
