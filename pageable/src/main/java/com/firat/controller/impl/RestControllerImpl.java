package com.firat.controller.impl;

import com.firat.controller.IRestController;
import com.firat.model.Personel;
import com.firat.service.IPersonelService;
import com.firat.service.impl.PersonelServiceImpl;
import com.firat.utils.RestPageableRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/api/personel")
public class RestControllerImpl implements IRestController {

    @Autowired
    private IPersonelService personelService;



    @GetMapping("/list/pageable")
    @Override
    public Page<Personel> findAllPageable(RestPageableRequest restPageableRequest, Pageable pageable) {
        Pageable pageableReq = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "id"));
        return personelService.findAllPageable(pageableReq);
    }
}
