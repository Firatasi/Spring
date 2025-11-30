package com.firat.service.impl;

import com.firat.exception.BaseException;
import com.firat.exception.ErrorMessage;
import com.firat.exception.MessageType;
import com.firat.service.IAddressService;

public class AddressServiceImpl implements IAddressService{
    public void haho() {
        throw new BaseException(new ErrorMessage(null, MessageType.NO_RECORD_EXIST));
    }

}
