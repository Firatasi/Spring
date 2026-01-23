package com.demo.yemekmasasi.service;

import com.demo.yemekmasasi.dto.request.KimlikRequestDto;
import com.demo.yemekmasasi.dto.response.KimlikResponseDto;
import com.demo.yemekmasasi.entity.Kimlik;
import com.demo.yemekmasasi.mapper.FoodMapper;
import com.demo.yemekmasasi.mapper.KimlikMapper;
import com.demo.yemekmasasi.repository.KimlikRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class KimlikService {
    private final KimlikRepository kimlikRepository;
    private final KimlikMapper kimlikMapper;

    public KimlikService(KimlikRepository kimlikRepository, KimlikMapper kimlikMapper, FoodMapper foodMapper) {
        this.kimlikRepository = kimlikRepository;
        this.kimlikMapper = kimlikMapper;
    }

    public KimlikResponseDto save(KimlikRequestDto kimlikRequestDto) {
        return null;
    }

}
