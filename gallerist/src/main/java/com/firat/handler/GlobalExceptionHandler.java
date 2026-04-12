package com.firat.handler;

import com.firat.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.sql.Time;
import java.util.*;

@ControllerAdvice//Tüm controller’lar için global çalışan bir “error manager”, yani genel hata yakalayıcı sınıf oluşturur.
public class GlobalExceptionHandler {

    @ExceptionHandler(value= BaseException.class) //ResponseEntity<ApiError<?>> soru işareti dönen tipin ne olacağı belli olmadığı için kullanılır
    public ResponseEntity<ApiError<?>> handleBaseException(BaseException ex, WebRequest request){ //Eğer projede bir yerde BaseException veya onun türevleri fırlarsa, bu metot çalışsın.
       return ResponseEntity.badRequest().body(createApiError(ex.getMessage(),request));//uygulkamada kendi fırlattığımız exceptionlar baseexception
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class}) //bu hata fırlarsa bu method çalışır.spring validation fırlattığı hatalarda çalışır
    public ResponseEntity<ApiError<Map<String, List<String>>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) { //firstname 10 karakter olmalıdır şifre boş bırakılamaz gibi hataların yakalndığı yer

        Map<String, List<String>> map = new HashMap<>();

        for (ObjectError objError : ex.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError)objError).getField();//obj erroru field error tipine çevirir

            if (map.containsKey(fieldName)) { //birtane fieldin birçok hatası olaabileceği için bir önceki hatalarıda kaydetmek için yazdık if else yazdık isim boş bırakılamaz numara olamaz gibi vs
                map.put(fieldName, addValue(map.get(fieldName), objError.getDefaultMessage()));
            }else  {
                map.put(fieldName, addValue(new ArrayList<>(), objError.getDefaultMessage()));
            }

        }

        return ResponseEntity.badRequest().body(createApiError(map,request));

    }

    private List<String> addValue(List<String> list, String newValue){
        list.add(newValue);
        return list;
    }


    private String hostName() {
        try {
            return Inet4Address.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }


    public <E> ApiError<E> createApiError(E message, WebRequest request){
        ApiError<E> apiError = new ApiError<>();
        apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        Exception<E> exception = new Exception<>();
        exception.setPath(request.getDescription(false));
        exception.setCreateTime(new Time(new Date().getTime()));
        exception.setMessage(message);

        apiError.setException(exception);

        return apiError;
    }
}
