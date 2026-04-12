package com.firat.controller;

import com.firat.dto.DtoAddress;
import com.firat.dto.DtoAddressIU;

public interface IRestAddressController {
    public  RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
}
