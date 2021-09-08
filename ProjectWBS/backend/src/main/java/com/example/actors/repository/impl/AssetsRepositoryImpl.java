package com.example.actors.repository.impl;

import com.example.actors.assets.JenaAssets;
import com.example.actors.repository.AssetsRepository;
import org.apache.jena.query.*;
import org.springframework.stereotype.Repository;

@Repository
public class AssetsRepositoryImpl implements AssetsRepository {

    @Override
    public String createEntityIdentifier(String search) {
        search = search.replaceAll(" ", "_");

        String queryString = "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix dbo: <http://dbpedia.org/ontology/> " +
                "SELECT ?uri where { " +
                "dbr:" + search + " dbo:wikiPageRedirects ?uri . " +
                "}";

        Query query = QueryFactory.create(queryString);
        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            if(resultSet.hasNext()) {
                return resultSet.nextSolution().get("uri").toString();
            }
        } catch (Exception e) {
            System.out.println("============" + e.getLocalizedMessage() + "\n" + e.getCause() + "\n\n");
        }
        return "http://dbpedia.org/resource/" + search;
    }



    @Override
    public String createEntityIdentifier2(String search) {
        search = search.replaceAll(" ", "_");

        String queryString = "prefix dbc: <http://dbpedia.org/resource/Category:> " +
                "prefix dbo: <http://dbpedia.org/ontology/> " +
                "SELECT ?uri where { " +
                "dbc:" + search + " dbo:wikiPageRedirects ?uri . " +
                "}";

        Query query = QueryFactory.create(queryString);
        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            if(resultSet.hasNext()) {
                return resultSet.nextSolution().get("uri").toString();
            }
        } catch (Exception e) {
            System.out.println("============" + e.getLocalizedMessage() + "\n" + e.getCause() + "\n\n");
        }
        return "http://dbpedia.org/resource/Category:" + search;
    }
}
