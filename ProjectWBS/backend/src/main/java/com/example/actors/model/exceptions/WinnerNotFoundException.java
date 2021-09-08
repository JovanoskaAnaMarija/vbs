package com.example.actors.model.exceptions;

public class WinnerNotFoundException extends Exception {
    public WinnerNotFoundException (String winnerName) {
        super(winnerName + " not found! Check what are you typing!!!");
    }
}
