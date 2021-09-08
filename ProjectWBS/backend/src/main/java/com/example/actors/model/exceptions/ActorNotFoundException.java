package com.example.actors.model.exceptions;

public class ActorNotFoundException extends Exception{

    public ActorNotFoundException (String actorName) {
        super(actorName + " not found! Check what are you typing!!!");
    }
}
