package com.firat.utils;

import com.firat.model.Personel;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.swing.*;

@UtilityClass //nesne türetilmez bütün methodlar statik olur her yerden erişilebilir(statik) bu yüzden işaretleriz
public class PageUtil {

    public boolean isNullOrEmpty(String value){
        return value == null || value.trim().length() == 0;
    }

    public Pageable toPageable(RestPageableRequest request){

        if (!isNullOrEmpty(request.getColumnName())){
            Sort sortBy = request.equals(request) ? Sort.by(Sort.Direction.ASC, request.getColumnName()) : Sort.by(Sort.Direction.DESC, request.getColumnName());
            return PageRequest.of(request.getPageNumber(), request.getPageSize(), sortBy);

        }

        return PageRequest.of(request.getPageNumber(), request.getPageSize());
    }

}
