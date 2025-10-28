package com.firat.exception;

public class BaseException extends RuntimeException{
    public BaseException(){}

    public BaseException(ErrorMessage errorMEssage){
        super(errorMEssage.prepareErrorMessage());
    }

}
