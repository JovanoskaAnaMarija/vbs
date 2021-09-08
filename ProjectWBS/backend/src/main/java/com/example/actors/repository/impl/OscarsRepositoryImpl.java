package com.example.actors.repository.impl;


import com.example.actors.assets.JenaAssets;
import com.example.actors.model.Oscars;
import com.example.actors.model.dto.OscarsDTO;
import com.example.actors.model.dto.OscarsInfoDTO;
import com.example.actors.repository.OscarsRepository;
import com.example.actors.utils.Utils;
import org.apache.jena.query.*;
import org.springframework.stereotype.Repository;


@Repository
public class OscarsRepositoryImpl implements OscarsRepository {


    @Override
    public void addOscarsBaseInfo(String oscarsURI, Oscars oscars) {
        String queryOscarsBaseInfo = "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "prefix dbp:  <http://dbpedia.org/property/>" +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                "SELECT * where { " +
                "     OPTIONAL { dbr:Academy_Awards rdfs:label ?label. } " +
                "     OPTIONAL { dbr:Academy_Awards dbo:abstract ?abstract. } " +
                "     OPTIONAL { dbr:Academy_Awards rdfs:comment ?comment. } " +
                "     OPTIONAL { dbr:Academy_Awards dbo:year ?year . } " +
                "     OPTIONAL { dbr:Academy_Awards dbp:country ?country .} " +
                "     OPTIONAL { dbr:Academy_Awards dbo:presenter ?presenter .} " +
                "FILTER (lang(?abstract) = \"en\" && lang(?label) = \"en\" && lang(?comment) = \"en\" && lang(?country) = \"en\")" +
                "}";

        Query query = QueryFactory.create(queryOscarsBaseInfo);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            if (resultSet.hasNext()) {
                Utils.addOscarsBaseInfo(oscars, resultSet.nextSolution());
            }
        }
    }

    @Override
    public OscarsInfoDTO getOscarsHistory() {
        String queryHistoryOfSport = "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "select distinct * where { " +
                "    dbr:History_of_film dbo:abstract ?historyFilmAbstract . " +
                "    dbr:Academy_Awards dbo:abstract ?oscarsAbstract ; " +
                "    rdfs:comment ?oscarsComment . " +
                "    FILTER (lang(?historyFilmAbstract) = \"en\" &&  lang(?oscarsAbstract) = \"en\" && lang(?oscarsComment) = \"en\")" +
                "}";

        Query query = QueryFactory.create(queryHistoryOfSport);
        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            if (resultSet.hasNext()) {
                QuerySolution qs = resultSet.nextSolution();

                OscarsInfoDTO dto = new OscarsInfoDTO();
                dto.setHistoryFilmAbstract(qs.getLiteral("historyFilmAbstract").getLexicalForm());
                dto.setOscarsAbstract(qs.getLiteral("oscarsAbstract").getLexicalForm());
                dto.setOscarsComment(qs.getLiteral("oscarsComment").getLexicalForm());
                return dto;
            }
        }
        return null;
    }

    @Override
    public OscarsDTO getOscarsData() {
        String queryOscarsBaseInfo = "prefix dbo: <http://dbpedia.org/ontology/> " +
                "prefix dbr: <http://dbpedia.org/resource/> " +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "prefix dbp:  <http://dbpedia.org/property/>" +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                "SELECT * where { " +
                "     OPTIONAL { dbr:Academy_Awards rdfs:label ?label. } " +
                "     OPTIONAL { dbr:Academy_Awards dbo:abstract ?abstract. } " +
                "     OPTIONAL { dbr:Academy_Awards rdfs:comment ?comment. } " +
                "     OPTIONAL { dbr:Academy_Awards dbo:year ?year . } " +
                "     OPTIONAL { dbr:Academy_Awards dbp:country ?country .} " +
                "     OPTIONAL { dbr:Academy_Awards dbo:presenter ?presenter .} " +
                "FILTER (lang(?abstract) = \"en\" && lang(?label) = \"en\" && lang(?comment) = \"en\" && lang(?country) = \"en\")" +
                "}";
        Query query = QueryFactory.create(queryOscarsBaseInfo);
        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(JenaAssets.SPARQLEndpoint, query)) {
            ResultSet resultSet = queryExecution.execSelect();
            if (resultSet.hasNext()) {
                QuerySolution qs = resultSet.nextSolution();

                OscarsDTO dto = new OscarsDTO();
                dto.setLabel(qs.getLiteral("label").getLexicalForm());
                dto.setComment(qs.getLiteral("comment").getLexicalForm());
                dto.setDescription(qs.getLiteral("abstract").getLexicalForm());
                dto.setYear(qs.getLiteral("year").getLexicalForm());
                dto.setCountry(qs.getLiteral("country").getLexicalForm());
                dto.setPresenter(qs.getResource("presenter").getLocalName());

                return dto;
            }

        }
        return null;
    }
}

