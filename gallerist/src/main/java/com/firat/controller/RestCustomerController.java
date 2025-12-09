package com.firat.controller;

import com.firat.dto.DtoCustomer;
import com.firat.dto.DtoCustomerIU;

public interface RestCustomerController {

    public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);

}
