package com.firat.controller;

import com.firat.dto.DtoCar;
import com.firat.dto.DtoCarIU;

public interface IRestCarController {

	public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
}
