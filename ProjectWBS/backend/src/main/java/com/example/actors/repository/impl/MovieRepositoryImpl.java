package com.example.actors.repository.impl;

import com.example.actors.assets.JenaAssets;
import com.example.actors.model.Actor;
import com.example.actors.model.Movie;
import com.example.actors.repository.MovieRepository;
import com.example.actors.utils.Utils;
import org.apache.jena.query.*;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    @Override
    public void addMovieBaseInfo(String movieURI, Movie movie) {
        String queryMovieBaseInfo = "prefix dbp: <http://dbpedia.org/property/> " +
                "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "SELECT DISTINCT " +
                "    ?label ?abstract ?budget ?cinematoGraphy ?director ?distributor ?musicComposer ?country  " +
                " WHERE { " +
                "     OPTIONAL { <" + movieURI + "> rdfs:label ?label . } " +
                "     OPTIONAL { <" + movieURI + "> dbo:abstract ?abstract . } " +
                "     OPTIONAL { <" + movieURI + "> dbo:budget ?budget . } " +
                "     OPTIONAL { <" + movieURI + ">  dbo:cinematography ?cinematoGraphy . } " +
                "     OPTIONAL { <" + movieURI + "> dbo:director ?director . } " +
                "     OPTIONAL { <" + movieURI + "> dbo:distributor ?distributor . } " +
                "     OPTIONAL { <" + movieURI + "> dbo:musicComposer ?musicComposer . } " +
                "     OPTIONAL { <" + movieURI + "> dbp:country ?country . } " +
                "     OPTIONAL { <" + movieURI + "> dbo:caption ?caption . } " +
                "    FILTER( " +
                "        lang(?label) = \"en\" && " +
                "        lang(?abstract) = \"en\" && " +
                "        lang(?country) = \"en\"  " +
                "    ) " +
                "}";

        Query query = QueryFactory.create(queryMovieBaseInfo);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            if (resultSet.hasNext()) {
                Utils.addMovieBaseInfo(movie, resultSet.nextSolution());
            }
        }
    }


    @Override
    public void addMovieProducers(String movieURI, Movie movie) {
        String queryMovieProducers = "prefix dbp: <http://dbpedia.org/property/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix dbo: <http://dbpedia.org/ontology/> " +
                "SELECT DISTINCT " +
                "    ?producer " +
                "WHERE { " +
                "    <" + movieURI + "> dbo:producer ?producer . " +
                "}";

        Query query = QueryFactory.create(queryMovieProducers);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution sln = resultSet.nextSolution();
                if (sln.contains("producer") &&
                        sln.get("producer") != null &&
                        sln.get("producer").isResource() &&
                        sln.getResource("producer").getLocalName().trim().length() > 0
                ) {
                    movie.getProducers().add(sln.getResource("producer").getLocalName());
                }
            }
        }
    }

    @Override
    public void addMovieProductionCompanies(String movieURI, Movie movie) {
        String queryMovieProductionCompanies = "prefix dbp: <http://dbpedia.org/property/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix dbo: <http://dbpedia.org/ontology/> " +
                "SELECT DISTINCT " +
                "    ?productionCompany " +
                "WHERE { " +
                "    <" + movieURI + "> dbo:productionCompany ?productionCompany . " +
                "}";

        Query query = QueryFactory.create(queryMovieProductionCompanies);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution sln = resultSet.nextSolution();
                if (sln.contains("productionCompany") &&
                        sln.get("productionCompany") != null &&
                        sln.get("productionCompany").isResource() &&
                        sln.getResource("productionCompany").getLocalName().trim().length() > 0
                ) {
                    movie.getProductionCompanies().add(sln.getResource("productionCompany").getLocalName());
                }
            }
        }
    }



    @Override
    public void addMovieActors(String movieURI, Movie movie) {
        String queryMovieActors = "prefix dbp: <http://dbpedia.org/property/> " +
                "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "SELECT DISTINCT " +
                "    ?actor ?name ?fullName  ?thumbnail " +
                "    ?abstract ?comment ?birthDate ?birthPlace ?yearsActive" +
                " WHERE { " +
                " <" + movieURI +"> dbo:starring ?actor. " +
                "    OPTIONAL { ?actor dbp:name ?name. } " +
                "    OPTIONAL { ?actor dbo:birthName ?fullName. } " +
                "    OPTIONAL { ?actor dbo:thumbnail ?thumbnail. } " +
                "    OPTIONAL { ?actor dbo:abstract ?abstract. } " +
                "    OPTIONAL { ?actor rdfs:comment ?comment. } " +
                "    OPTIONAL { ?actor dbo:birthDate ?birthDate. } " +
                "    OPTIONAL { ?actor dbp:birthPlace ?birthPlace. } " +
                "    OPTIONAL { ?actor dbp:yearsActive ?yearsActive. } " +
                "    FILTER (lang(?abstract) = \"en\" && lang(?comment) =\"en\") " +
                "} ";

        Query query = QueryFactory.create(queryMovieActors);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            while (resultSet.hasNext()) {
                Actor actor = new Actor();
                Utils.addActorBaseInfo(actor, resultSet.nextSolution());
                movie.getActors().add(actor);
            }
        }
    }




}

