package com.example.actors.repository.impl;

import com.example.actors.assets.JenaAssets;
import com.example.actors.model.Actor;
import com.example.actors.model.dto.ActorAwardDTO;
import com.example.actors.model.exceptions.ActorNotFoundException;
import com.example.actors.repository.ActorRepository;
import com.example.actors.utils.Utils;
import org.apache.jena.query.*;
import org.springframework.stereotype.Repository;

@Repository
public class ActorRepositoryImpl implements ActorRepository {

    @Override
    public void addActorBaseInfo(String uri, Actor actor) throws ActorNotFoundException {
        String queryActor = "prefix dbp: <http://dbpedia.org/property/> " +
                "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "SELECT * WHERE { " +
                "OPTIONAL { <" + uri + "> dbp:name ?name. } " +
                "OPTIONAL { <" + uri + "> dbo:birthName ?fullName. } " +
                "OPTIONAL { <" + uri + "> dbo:thumbnail ?thumbnail. } " +
                "OPTIONAL { <" + uri + "> dbo:abstract ?abstract. } " +
                "OPTIONAL { <" + uri + "> dbp:yearsActive ?yearsActive . } " +
                "OPTIONAL { <" + uri + "> rdfs:comment ?comment. } " +
                "OPTIONAL { <" + uri + "> dbo:birthDate ?birthDate. } " +
                "OPTIONAL { <" + uri + "> dbp:birthPlace ?birthPlace. } " +
                "OPTIONAL { <" + uri + "> dbo:deathPlace ?deathPlace. } " +
                "OPTIONAL { <" + uri + "> dbp:deathDate ?deathDate. } " +
                "FILTER (lang(?abstract) = \"en\" && lang(?comment) = \"en\" && lang(?name) = \"en\") " +
                "}";

        Query query = QueryFactory.create(queryActor);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            if(resultSet.hasNext()) {
                Utils.addActorBaseInfo(actor, resultSet.nextSolution());
            } else throw new ActorNotFoundException(uri.split("/")[uri.split("/").length-1]);
        }
    }

    @Override
    public void addActorQuotes(String uri, Actor actor) {
        String queryActorQuotes = "prefix dbp: <http://dbpedia.org/property/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "SELECT * where { " +
                "<" + uri + "> dbp:quote ?quote." +
                "FILTER (lang(?quote) = \"en\") " +
                "}";

        Query queryQuotes = QueryFactory.create(queryActorQuotes);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, queryQuotes)) {
            ResultSet resultSet = queryExecution.execSelect();
            if(resultSet.hasNext()) {
                Utils.addActorQuotes(actor, resultSet);
            }
        }
    }

    @Override
    public void addActorAwards(String uri, Actor actor) {
    String queryActorAwards = "prefix dbo: <http://dbpedia.org/ontology/> " +
            "prefix dbr: <http://dbpedia.org/resource/> " +
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
            "prefix dbp: <http://dbpedia.org/property/> " +
            "SELECT ?award ?abstract ?comment ?wins ?nominations" +
            "WHERE { " +
            " <" + uri + ">  dbo:award ?award. " +
            "   ?award dbo:abstract ?abstract." +
            "   ?award rdfs:comment ?comment." +
            "   ?award dbp:wins ?wins ."+
            "?award dbp:nominations ?nominations ."+
            "    FILTER ( " +
            "        lang(?abstract) = \"en\" && " +
            "        lang(?comment) = \"en\" " +
            "    ) " +
            "}";

        Query query = QueryFactory.create(queryActorAwards);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            Utils.addActorAwards(actor, resultSet);
        }

    }

    @Override
    public void addActorAw(String uri, Actor actor) {
        String queryActorAwards = "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "prefix dbp: <http://dbpedia.org/property/> " +
                "SELECT  ?comment " +
                "WHERE { " +
                " <" + uri + ">  dbo:award ?award. " +
                "   ?award dbo:abstract ?abstract." +
                "   ?award rdfs:comment ?comment." +
                "   ?award dbp:wins ?wins."+
                "?award dbp:nominations ?nominations."+
                "    FILTER ( " +
                "        lang(?abstract) = \"en\" && " +
                "        lang(?comment) = \"en\" " +
                "    ) " +
                "}";

        Query query = QueryFactory.create(queryActorAwards);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            Utils.addActorAw(actor, resultSet);
        }

    }

    @Override
    public void addActorWins(String uri, Actor actor) {
        String queryActorWins = "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "prefix dbp: <http://dbpedia.org/property/> " +
                "SELECT  ?wins " +
                "WHERE { " +
                " <" + uri + ">  dbo:award ?award. " +
                "   ?award dbo:abstract ?abstract." +
                "   ?award rdfs:comment ?comment." +
                "   ?award dbp:wins ?wins."+
                "?award dbp:nominations ?nominations."+
                "    FILTER ( " +
                "        lang(?abstract) = \"en\" && " +
                "        lang(?comment) = \"en\" " +
                "    ) " +
                "}";
        Query queryQuotes = QueryFactory.create(queryActorWins);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, queryQuotes)) {
            ResultSet resultSet = queryExecution.execSelect();
            if(resultSet.hasNext()) {
                Utils.addActorWins(actor, resultSet.nextSolution());
            }
        }

    }

    @Override
    public void addActorNominations(String uri, Actor actor) {
        String queryActorNominations = "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "prefix dbp: <http://dbpedia.org/property/> " +
                "SELECT  ?nominations " +
                "WHERE { " +
                " <" + uri + ">  dbo:award ?award. " +
                "   ?award dbo:abstract ?abstract." +
                "   ?award rdfs:comment ?comment." +
                "   ?award dbp:wins ?wins."+
                "?award dbp:nominations ?nominations."+
                "    FILTER ( " +
                "        lang(?abstract) = \"en\" && " +
                "        lang(?comment) = \"en\" " +
                "    ) " +
                "}";
        Query queryQuotes = QueryFactory.create(queryActorNominations);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, queryQuotes)) {
            ResultSet resultSet = queryExecution.execSelect();
            if(resultSet.hasNext()) {
                Utils.addActorNominations(actor, resultSet.nextSolution());
            }
        }

    }


    @Override
    public void addActorMovies(String uri, Actor actor) {
        String queryActorMovies = "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "SELECT ?movie ?label ?abstract " +
                "WHERE { " +
                "    ?movie dbo:starring <" + uri + ">  . " +
                "    ?movie rdfs:label ?label; " +
                "        dbo:abstract ?abstract . " +
                "    FILTER ( " +
                "        lang(?abstract) = \"en\" && " +
                "        lang(?label) = \"en\" " +
                "    ) " +
                "}";

        Query query = QueryFactory.create(queryActorMovies);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            Utils.addActorMovies(actor, resultSet);
        }

    }

}


