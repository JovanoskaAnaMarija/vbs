package com.example.actors.repository;

import com.example.actors.model.Actor;
import com.example.actors.model.dto.ActorAwardDTO;
import com.example.actors.model.exceptions.ActorNotFoundException;

public interface ActorRepository {

    void addActorBaseInfo(String uri, Actor actor) throws ActorNotFoundException;

    void addActorQuotes(String uri, Actor actor);

    void addActorAwards(String uri, Actor actor);

    void addActorAw(String uri, Actor actor);

    void addActorWins(String uri, Actor actor);

    void addActorNominations(String uri, Actor actor);

    void addActorMovies(String uri, Actor actor);
}
