package com.firat.controller;

import com.firat.dto.DtoCustomer;

public interface ICustomerController {

    public DtoCustomer findCustomerById(Long id);

}
