package com.firat.service;

import com.firat.dto.DtoAddress;
import com.firat.dto.DtoAddressIU;

public interface IAddressService {

    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
}
