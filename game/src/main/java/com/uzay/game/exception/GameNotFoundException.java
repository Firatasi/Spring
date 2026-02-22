package com.uzay.game.exception;

public class GameNotFoundException extends RuntimeException{
    public GameNotFoundException(int value, String message) {
        super(message);
    }
}
