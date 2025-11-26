package com.firat.controller.impl;

import com.firat.utils.PageUtil;
import com.firat.utils.RestPageableRequest;
import org.springframework.data.domain.Pageable;

public class RestBaseController {
    public Pageable toPageable(RestPageableRequest request){

        return PageUtil.toPageable(request);

    }
}
