package com.firat.repository;

import com.firat.dto.DtoCustomer;
import com.firat.dto.DtoCustomerIU;
import com.firat.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {



}
