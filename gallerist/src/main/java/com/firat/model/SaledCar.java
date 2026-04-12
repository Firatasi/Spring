package com.firat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "saledCar",
uniqueConstraints = {@UniqueConstraint(columnNames = {"galleris_id", "car_id","customer_id"},name = "uq_gallerist_car_customer")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaledCar extends BaseEntity{
    @ManyToOne
    private Gallerist gallerist;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Customer customer;
}
