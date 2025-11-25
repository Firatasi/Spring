package com.firat.controller;

import com.firat.model.Personel;
import com.firat.utils.RestPageableRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRestController {
    public Page<Personel> findAllPageable(RestPageableRequest restPageableRequest, Pageable pageable);
}
