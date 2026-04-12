package com.firat.service;

import com.firat.model.Personel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPersonelService {
    Page<Personel> findAllPageable(Pageable pageable);
}
