package com.firat.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    private List<String> addMapValue(List<String> list, String newValue) {
        list.add(newValue);
        return list;
    }


    //spring validation hatalarını dönmek
    @ExceptionHandler(value = MethodArgumentNotValidException.class) // böyle bir hata gelirse bu metodu çalıştır
    public ResponseEntity<ApiError> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        Map<String, List<String>> errorsMap = new HashMap<>();
        for (ObjectError objError : e.getBindingResult().getAllErrors()) {
        String fieldName = ((FieldError) objError).getField();
        if (errorsMap.containsKey(fieldName)) {
            errorsMap.put(fieldName, addMapValue(errorsMap.get(fieldName), objError.getDefaultMessage()));
        }else {
            errorsMap.put(fieldName, addMapValue(new ArrayList<>(), objError.getDefaultMessage()));
        }
        }
        return ResponseEntity.badRequest().body(createApiError(errorsMap));
    }

    private <T> ApiError<T> createApiError(T errors) {
        ApiError<T> apiError = new ApiError<T>();
        apiError.setId(UUID.randomUUID().toString());
        apiError.setErrorTime(new Date());
        apiError.setErrors(errors);

        return apiError;
    }

}
