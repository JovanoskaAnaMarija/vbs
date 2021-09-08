package com.example.actors.repository.impl;


import com.example.actors.assets.JenaAssets;
import com.example.actors.model.AcademyAward;
import com.example.actors.repository.AcademyAwardRepository;
import com.example.actors.utils.Utils;
import org.apache.jena.query.*;
import org.springframework.stereotype.Repository;

@Repository
public class AcademyAwardRepositoryImpl implements AcademyAwardRepository {


    @Override
    public void addAcademyAwardBaseInfo(String awardURI, AcademyAward academyAward) {
        String queryAcademyAwardBaseInfo = "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "prefix dbc: <http://dbpedia.org/resource/Category:>"+
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                "SELECT DISTINCT * where { " +
                "     OPTIONAL { <" + awardURI + "> rdfs:label ?label. } " +
                "     OPTIONAL { <" + awardURI + "> dbo:abstract ?abstract. } " +
                "     OPTIONAL { <" + awardURI + "> dbo:comment ?comment. } " +
                "FILTER (lang(?abstract) = \"en\" && lang(?label) = \"en\" && lang(?comment) = \"en\")" +
                "}";

        Query query = QueryFactory.create(queryAcademyAwardBaseInfo);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            if (resultSet.hasNext()) {
                Utils.addAcademyAwardBaseInfo(academyAward, resultSet.nextSolution());
            }
        }
    }



    @Override
    public void addAwardActors(String awardURI, AcademyAward academyAward) {
        String queryActorsOfAward = "prefix dbp: <http://dbpedia.org/property/> " +
                "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "prefix dbc: <http://dbpedia.org/resource/Category:>"+
                "PREFIX dct:  <http://purl.org/dc/terms/>"+
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"+
                "SELECT DISTINCT *" +
                "where" +
                "{" +
                "?actor rdf:type dbo:Person." +
                "?actor dct:subject  <" + awardURI +"> . " +
//                "?actor rdfs:label ?name. " +
                "OPTIONAL{?actor dbp:name ?name.}" +
//                "OPTIONAL{?actor rdfs:label ?label.}" +
//                "OPTIONAL{?actor dbo:birthName ?fullName.}" +
                "OPTIONAL{?actor dbo:thumbnail ?thumbnail.}" +
                "OPTIONAL{?actor dbo:abstract ?abstract.}" +
                "OPTIONAL{?actor rdfs:comment ?comment}" +
                "OPTIONAL{?actor dbp:yearsActive ?yearsActive}" +
                "OPTIONAL{?actor dbo:birthDate ?birthDate}" +
//                "OPTIONAL{?actor dbo:birthPlace ?birthPlace}" +
                "FILTER(lang(?abstract)=\"en\" && lang(?comment)=\"en\" && lang(?name)=\"en\")" +
                "} ";

        Query query = QueryFactory.create(queryActorsOfAward);
        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            if (resultSet.hasNext()) {
                Utils.addAwardActors(academyAward, resultSet);
            }
        }

    }

    @Override
    public void addAwardMovies(String awardURI, AcademyAward academyAward) {
        String queryAwardMovies = "prefix dbp: <http://dbpedia.org/property/> " +
                "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "prefix dbc: <http://dbpedia.org/resource/Category:>"+
                "PREFIX dct:  <http://purl.org/dc/terms/>"+
                "SELECT distinct *" +
                "where" +
                "{" +
                "?movie rdf:type dbo:Film." +
                "?movie dct:subject  <" + awardURI +"> . " +
                "OPTIONAL{?movie rdfs:label ?label.}" +
                "OPTIONAL{?movie dbo:abstract ?abstract.}" +
                "OPTIONAL{?movie dbo:budget ?budget.}" +
                "OPTIONAL{?movie dbo:cinematography ?cinematography.}" +
                "OPTIONAL{?movie dbo:director ?director.}" +
                "OPTIONAL{?movie dbo:distributor ?distributor.}" +
                "OPTIONAL{?movie dbo:musicComposer ?musicComposer.}" +
                "OPTIONAL{?movie dbp:country ?country.}"+
                "FILTER(lang(?abstract)=\"en\" && lang(?label)=\"en\")" +
                "} ";
        Query query = QueryFactory.create(queryAwardMovies);
        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            if (resultSet.hasNext()) {
                Utils.addAwardMovies(academyAward, resultSet);
            }
        }
    }


}
