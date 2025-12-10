package com.firat.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firat.dto.DtoAddress;
import com.firat.dto.DtoCar;
import com.firat.dto.DtoGallerist;
import com.firat.dto.DtoGalleristCar;
import com.firat.dto.DtoGalleristCarIU;
import com.firat.exception.BaseException;
import com.firat.exception.ErrorMessage;
import com.firat.exception.MessageType;
import com.firat.model.Car;
import com.firat.model.Gallerist;
import com.firat.model.GalleristCar;
import com.firat.repository.CarRepository;
import com.firat.repository.GalleristCarRepository;
import com.firat.repository.GalleristRepository;
import com.firat.service.IGalleristCarService;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService{
	
	@Autowired
	private GalleristCarRepository galleristCarRepository;
	
	@Autowired
	private GalleristRepository galleristRepository;

	@Autowired
	private CarRepository carRepository;
	
	private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		
		Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
		if(optGallerist.isEmpty()) {
			throw new BaseException(new ErrorMessage(dtoGalleristCarIU.getGalleristId().toString(), MessageType.NO_RECORD_EXIST));
		}

		Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());
		if(optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(dtoGalleristCarIU.getCarId().toString(), MessageType.NO_RECORD_EXIST));
		}
		
		
		GalleristCar galleristCar = new GalleristCar();
		galleristCar.setCreatTime(new Date());
		galleristCar.setGallerist(optGallerist.get());
		galleristCar.setCar(optCar.get());
		
		return galleristCar;
		
	}
	
	@Override
	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoCar dtoCar = new DtoCar();
		
		DtoAddress dtoAddress = new DtoAddress();
		
		GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));
		
		BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);
		BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist);
		BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAdress(), dtoAddress);
		
		BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);
		
		
		dtoGallerist.setAddress(dtoAddress);
		dtoGalleristCar.setGallerist(dtoGallerist);
		dtoGalleristCar.setCar(dtoCar);
		
		return dtoGalleristCar;
	}

}
