package com.firat.controller;

import com.firat.dto.DtoGallerist;
import com.firat.dto.DtoGalleristIU;

public interface IRestGalleristController {

	public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);
}
