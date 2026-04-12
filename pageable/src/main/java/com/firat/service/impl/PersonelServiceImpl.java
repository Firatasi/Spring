package com.firat.service.impl;

import com.firat.model.Personel;
import com.firat.repository.PersonelRepository;
import com.firat.service.IPersonelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonelServiceImpl implements IPersonelService {

    @Autowired
    private PersonelRepository personelRepository;

    @Override
    public Page<Personel> findAllPageable(Pageable pageable) {
        Page<Personel> page =  personelRepository.findAll(pageable);
        return page;
    }
}
