package com.demo.ulke_baskent.exception;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(int value, String message) {
        super(message);
    }
}
