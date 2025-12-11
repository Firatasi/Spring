package com.firat.controller;

import com.firat.dto.DtoSaledCar;
import com.firat.dto.DtoSaledCarIU;

public interface IRestSaledCarController {

	public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);
}
