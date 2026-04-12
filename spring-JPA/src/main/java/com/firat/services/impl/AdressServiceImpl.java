package com.firat.services.impl;

import com.firat.dto.DtoAdress;
import com.firat.dto.DtoCustomer;
import com.firat.entites.Adress;
import com.firat.repository.AdressRepository;
import com.firat.services.IAdressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdressServiceImpl implements IAdressService {
    @Autowired
    private AdressRepository adressRepository;

    public DtoAdress findAdressById(Long id) {

        DtoAdress dtoAdress = new DtoAdress();

        Optional<Adress> optional = adressRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        Adress adress = optional.get();
        BeanUtils.copyProperties(adress, dtoAdress);

        DtoCustomer dtoCustomer = new DtoCustomer();
        dtoCustomer.setId(adress.getCustomer().getId());
        dtoCustomer.setName(adress.getCustomer().getName());
        //dtoCustomer.setAdress(dtoAdress);

        dtoAdress.setCustomer(dtoCustomer);

        return dtoAdress;
    }
}
