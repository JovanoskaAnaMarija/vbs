package com.example.actors.service;

import com.example.actors.model.Actor;
import com.example.actors.model.exceptions.ActorNotFoundException;

public interface ActorService {
    Actor getActorInfo(String name) throws ActorNotFoundException;

    Actor getActorInfoWithURI(String URI) throws ActorNotFoundException;
}
