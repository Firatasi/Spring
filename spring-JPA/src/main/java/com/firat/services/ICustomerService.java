package com.firat.services;

import com.firat.dto.DtoCustomer;

public interface ICustomerService {

    public DtoCustomer findCustomerById(Long id);

}
