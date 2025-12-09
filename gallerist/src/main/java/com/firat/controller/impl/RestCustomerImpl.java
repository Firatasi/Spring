package com.firat.controller.impl;

import com.firat.controller.RestCustomerController;
import com.firat.controller.RootEntity;
import com.firat.dto.DtoCustomer;
import com.firat.dto.DtoCustomerIU;
import com.firat.service.ICustomerService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.firat.controller.RootEntity.ok;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerImpl implements RestCustomerController {

    private ICustomerService  customerService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {

        return ok(customerService.saveCustomer(dtoCustomerIU));
    }
}
