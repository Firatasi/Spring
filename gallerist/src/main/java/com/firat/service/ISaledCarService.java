package com.firat.service;

import com.firat.dto.DtoSaledCar;
import com.firat.dto.DtoSaledCarIU;

public interface ISaledCarService {

	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
}
