package com.uzay.game.exception;

public class GameAlreadyExistException extends RuntimeException{
    public GameAlreadyExistException(String message) {
        super(message);
    }
}
