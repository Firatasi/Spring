package com.firat.services.impl;

import com.firat.dto.DtoAdress;
import com.firat.dto.DtoCustomer;
import com.firat.entites.Adress;
import com.firat.entites.Customer;
import com.firat.repository.CustomerRepository;
import com.firat.services.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public DtoCustomer findCustomerById(Long id) {
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAdress dtoAdress = new DtoAdress();

        Optional<Customer> optional =  customerRepository.findById(id);
        if(optional.isEmpty()) {
            return null;
        }
            Customer customer = optional.get();
            Adress adress = customer.getAdress();

            BeanUtils.copyProperties(customer,dtoCustomer);
            BeanUtils.copyProperties(adress,dtoAdress);

            dtoCustomer.setAdress(dtoAdress);


        return dtoCustomer;
    }

}
