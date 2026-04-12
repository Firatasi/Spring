package com.firat.service;

import com.firat.dto.DtoCustomer;
import com.firat.dto.DtoCustomerIU;

public interface ICustomerService {
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
}
