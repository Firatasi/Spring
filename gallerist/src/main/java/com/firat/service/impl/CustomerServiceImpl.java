package com.firat.service.impl;

import com.firat.dto.DtoAccount;
import com.firat.dto.DtoAddress;
import com.firat.dto.DtoCustomer;
import com.firat.dto.DtoCustomerIU;
import com.firat.exception.BaseException;
import com.firat.exception.ErrorMessage;
import com.firat.exception.MessageType;
import com.firat.model.Account;
import com.firat.model.Address;
import com.firat.model.Customer;
import com.firat.repository.AccountRepository;
import com.firat.repository.AddressRepository;
import com.firat.repository.CustomerRepository;
import com.firat.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Customer createCustomer(DtoCustomerIU dtoCustomerIU) {

        Optional<Address> optAddress = addressRepository.findById(dtoCustomerIU.getId());
        if (optAddress.isEmpty()) {
            throw new BaseException(new ErrorMessage(dtoCustomerIU.getAdressId().toString(), MessageType.NO_RECORD_EXIST));
        }

        Optional<Account> optAccount= accountRepository.findById(dtoCustomerIU.getAccountId().getId());
        if (optAccount.isEmpty()) {

            throw new BaseException(new ErrorMessage(dtoCustomerIU.getAccountId().toString(), MessageType.NO_RECORD_EXIST));
        }

        Customer customer = new Customer();
        customer.setCreatTime(new Date());
        BeanUtils.copyProperties(dtoCustomerIU, customer);
        customer.setAdress(optAddress.get());
        customer.setAccount(optAccount.get());
        return customer;
    }



    @Override
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {

        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAddress  dtoAddress = new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();

        Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));
        BeanUtils.copyProperties(savedCustomer, dtoCustomer);
        BeanUtils.copyProperties(savedCustomer.getAdress(), dtoAddress);
        BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);

        dtoCustomer.setAdressId(dtoAddress);
        dtoCustomer.setAccountId(dtoAccount);

        return dtoCustomer;

    }
}
