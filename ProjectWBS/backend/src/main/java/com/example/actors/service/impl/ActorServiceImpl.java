package com.example.actors.service.impl;

import com.example.actors.model.Actor;
import com.example.actors.model.dto.ActorAwardDTO;
import com.example.actors.model.exceptions.ActorNotFoundException;
import com.example.actors.repository.ActorRepository;
import com.example.actors.repository.AssetsRepository;
import com.example.actors.service.ActorService;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final AssetsRepository assetsRepository;

    public ActorServiceImpl(ActorRepository actorRepository, AssetsRepository assetsRepository) {
        this.actorRepository = actorRepository;
        this.assetsRepository = assetsRepository;
    }

    @Override
    public Actor getActorInfo(String name) throws ActorNotFoundException {
        String uri = this.assetsRepository.createEntityIdentifier(name);

        Actor actor = new Actor();

        this.actorRepository.addActorBaseInfo(uri, actor);
        this.actorRepository.addActorQuotes(uri, actor);
        this.actorRepository.addActorMovies(uri, actor);
        //this.actorRepository.addActorAwards(uri, actor);
        this.actorRepository.addActorAw(uri, actor);
        this.actorRepository.addActorWins(uri, actor);
        this.actorRepository.addActorNominations(uri, actor);
        actor.setURI(uri);
        return actor;
    }

    @Override
    public Actor getActorInfoWithURI(String uri) throws ActorNotFoundException {
        Actor actor = new Actor();
        String tempUri = uri;

        uri = "http://dbpedia.org/resource/" + uri;

        this.actorRepository.addActorBaseInfo(uri, actor);
        this.actorRepository.addActorQuotes(uri, actor);
        this.actorRepository.addActorMovies(uri, actor);
        //this.actorRepository.addActorAwards(uri, actor);
        this.actorRepository.addActorAw(uri,actor);
        this.actorRepository.addActorWins(uri, actor);
        this.actorRepository.addActorNominations(uri, actor);
        actor.setURI(tempUri);
        return actor;
    }
}

