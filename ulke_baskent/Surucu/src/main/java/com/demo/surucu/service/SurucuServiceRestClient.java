package com.demo.surucu.service;

import com.demo.surucu.CarResponse;
import com.demo.ulke_baskent.exception.CarNotFoundException;
import com.userpe.dto.request.CarRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class SurucuServiceRestClient {

    private final RestClient restClient;
    public SurucuServiceRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public ResponseEntity<CarResponse> getCar(Long id) {

//        URI uri = UriComponentsBuilder
//                .fromUriString("/{id}")  BASE URL VAR İSE KULLANILMAZ
//                .buildAndExpand(id)
//                .toUri();

        ResponseEntity<CarResponse> responseEntity = restClient
                .get()
                .uri("/{id}",id) //uri şablonu ve parametre
//                .uri(id) base url yoksa direkt id yazılabilir yukarda uricomponent yapısı kullanılabilir
                .retrieve() //isteği gönder ve yanıtı al
                .onStatus(HttpStatusCode::is4xxClientError, ((request,response) ->{
                    throw new CarNotFoundException(HttpStatus.NOT_FOUND.value(),"car not found");
                }))
                .toEntity(CarResponse.class); //json yanıtını java nesnesine çevirir,Status code (200, 404, 500…),Header bilgileri,Body

        return responseEntity;

    }

    public CarResponse getCar2(Long id) {
        CarResponse carResponse = restClient
                .get()
                .uri("/{id}", id)
                .retrieve()
                .body(CarResponse.class);//Bu JSON’u al, CarResponse nesnesine çevir.Status code, header bilgilerini vermez
            return carResponse;
    }

    public void getCar3(Long id) {
        restClient
                .get()
                .uri("/{id}",id)
                .retrieve()
                .toBodilessEntity(); // response’un body kısmını hiç okumadan, sadece status code + header bilgilerini döndürür.
    }

    public ResponseEntity<List<CarResponse>> getAllCar() {
        ResponseEntity<List<CarResponse>> responseEntity = restClient
                .get()
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<CarResponse>>() {
                });
                return responseEntity;
    }

    public ResponseEntity<CarResponse> addCar(CarRequest carRequest) {
        ResponseEntity<CarResponse> entity = restClient
                .post()
                .header("Content-Type", "application/json")//post işlemleri için gerekli
                .body(carRequest)
                .retrieve()
                .toEntity(CarResponse.class);
        return entity;
    }


    public CarResponse addCar2(CarRequest carRequest) {
        CarResponse carResponse = restClient
                .post()
                .header("Content-Type", "application/json")//post işlemleri için gerekli
                .body(carRequest)
                .retrieve()
                .body(CarResponse.class);
        return carResponse;
    }

    public void addCar3(CarRequest carRequest) {
        restClient
                .post()
                .header("Content-Type", "application/json")//post işlemleri için gerekli
                .body(carRequest)
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<CarResponse> updateCar(Long id, CarRequest carRequest) {
        String path = "http://localhost:8080/api/car/v1/{id}";
        URI uri = UriComponentsBuilder
                .fromPath(path)
                .buildAndExpand(id)
                .toUri();


        return restClient
                .put()
                .uri(uri)
                .body(carRequest)
                .retrieve()
                .toEntity(CarResponse.class);

    }

    public void deleteCar(Long id) {
        restClient
                .delete()
                .uri("/{id}",id)
                .retrieve()
                .toEntity(CarResponse.class);
    }

}
