package com.firat.controller.impl;

import com.firat.controller.IAdressController;
import com.firat.dto.DtoAdress;
import com.firat.repository.AdressRepository;
import com.firat.services.IAdressService;
import com.firat.services.impl.AdressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/api/adress")
public class AdressControllerImpl implements IAdressController {

    @Autowired
    private IAdressService adressService;


    @GetMapping(path = "/list/{id}")
    @Override
    public DtoAdress findAdressById(@PathVariable(name = "id") Long id) {
        return adressService.findAdressById(id);

    }

}
