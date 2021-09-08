package com.example.actors.repository.impl;

import com.example.actors.model.Winners;
import com.example.actors.model.dto.WinnersDTO;
import com.example.actors.repository.WinnersRepository;
import org.apache.jena.query.*;
import org.springframework.stereotype.Repository;

import java.net.URI;

@Repository
public class WinnersRepositoryImpl implements WinnersRepository {

    @Override
    public WinnersDTO getBestDirectorsData() {
        String queryBestDirectors = "PREFIX bd: <http://www.bigdata.com/rdf#> \n"
                + "PREFIX p: <http://www.wikidata.org/prop/> \n"
                + "PREFIX pq: <http://www.wikidata.org/prop/qualifier/> \n"
                + "PREFIX ps: <http://www.wikidata.org/prop/statement/> \n"
                + "PREFIX wd: <http://www.wikidata.org/entity/> \n"
                + "PREFIX wdt: <http://www.wikidata.org/prop/direct/> \n"
                + "PREFIX wikibase: <http://wikiba.se/ontology#> \n"
                + " \n"
                + "SELECT DISTINCT ?item ?itemLabel ?awardLabel ?time \n"
                + "{ \n"
                + "  # Items with the Occupation(P 106) of Director(Q 3455803) or a subclass(P 279) \n"
                + "    ?item wdt:P106/wdt:P279* wd:Q3455803 ; \n"
                + "          # ... with an awarded(P 166) statement \n"
                + "          p:P166 ?awardStat . \n"
                + "  # Get the award (which is \"subject of\" XXth Academy Awards) \n"
                + "    ?awardStat pq:P805 ?award ; \n"
                + "               # ... that has the value Academy Award for Best Director(Q 103360) \n"
                + "               ps:P166 wd:Q103360 . \n"
                + "   # the \"point of time\" of the Academy Award \n"
                + "    ?award wdt:P585 ?time . \n"
                + "    SERVICE wikibase:label { \n"
                + "      # ... include the labels \n"
                + "        bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],en\" \n"
                + "    } \n"
                + "} \n"
                + "ORDER BY DESC(?time) \n";
        Query query = QueryFactory.create(queryBestDirectors);
        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService(String.valueOf(URI.create("https://query.wikidata.org/sparql")), query)) {
            ResultSet resultSet = queryExecution.execSelect();
            if (resultSet.hasNext()) {
                QuerySolution qs = resultSet.nextSolution();

                WinnersDTO dto = new WinnersDTO();
                dto.setItem(qs.getResource("item").getLocalName());
                dto.setItemLabel(qs.getLiteral("itemLabel").getLexicalForm());
                dto.setAwardLabel(qs.getLiteral("awardLabel").getLexicalForm());
                dto.setTime(qs.getLiteral("time").getLexicalForm());
                return dto;
            }

        }
        return null;

    }

    @Override
    public Winners addWinnersBestDirectors(Winners winner) {
        String queryBestDirectors = "PREFIX bd: <http://www.bigdata.com/rdf#> \n"
                + "PREFIX p: <http://www.wikidata.org/prop/> \n"
                + "PREFIX pq: <http://www.wikidata.org/prop/qualifier/> \n"
                + "PREFIX ps: <http://www.wikidata.org/prop/statement/> \n"
                + "PREFIX wd: <http://www.wikidata.org/entity/> \n"
                + "PREFIX wdt: <http://www.wikidata.org/prop/direct/> \n"
                + "PREFIX wikibase: <http://wikiba.se/ontology#> \n"
                + " \n"
                + "SELECT DISTINCT ?item ?itemLabel ?awardLabel ?time \n"
                + "{ \n"
                + "  # Items with the Occupation(P 106) of Director(Q 3455803) or a subclass(P 279) \n"
                + "    ?item wdt:P106/wdt:P279* wd:Q3455803 ; \n"
                + "          # ... with an awarded(P 166) statement \n"
                + "          p:P166 ?awardStat . \n"
                + "  # Get the award (which is \"subject of\" XXth Academy Awards) \n"
                + "    ?awardStat pq:P805 ?award ; \n"
                + "               # ... that has the value Academy Award for Best Director(Q 103360) \n"
                + "               ps:P166 wd:Q103360 . \n"
                + "   # the \"point of time\" of the Academy Award \n"
                + "    ?award wdt:P585 ?time . \n"
                + "    SERVICE wikibase:label { \n"
                + "      # ... include the labels \n"
                + "        bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],en\" \n"
                + "    } \n"
                + "} \n"
                + "ORDER BY DESC(?time) \n";
        Query query = QueryFactory.create(queryBestDirectors);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService("https://query.wikidata.org/sparql", query)) {
            ResultSet resultSet = queryExecution.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution qs = resultSet.nextSolution();
                String item = null,
                        itemLabel = null,
                        awardLabel = null,
                        time = null;

                if (qs.contains("item") && qs.get("item") != null)
                    item = qs.getResource("item").toString();
                if (qs.contains("itemLabel") && qs.get("itemLabel") != null && qs.get("itemLabel").isLiteral())
                    itemLabel = qs.getLiteral("itemLabel").getLexicalForm();
                if (qs.contains("awardLabel") && qs.get("awardLabel") != null && qs.get("awardLabel").isLiteral())
                    awardLabel = qs.getLiteral("awardLabel").getLexicalForm();
                if (qs.contains("time") && qs.get("time") != null && qs.get("time").isLiteral())
                    time = qs.getLiteral("time").getLexicalForm();

                winner.getBestDirectors().add(new WinnersDTO(item, itemLabel, awardLabel, time));
            }
        }

        return winner;
    }

    @Override
    public Winners addWinnersBestActors(Winners winner) {
        String queryBestActors = "PREFIX bd: <http://www.bigdata.com/rdf#> \n"
                + "PREFIX p: <http://www.wikidata.org/prop/> \n"
                + "PREFIX pq: <http://www.wikidata.org/prop/qualifier/> \n"
                + "PREFIX ps: <http://www.wikidata.org/prop/statement/> \n"
                + "PREFIX wd: <http://www.wikidata.org/entity/> \n"
                + "PREFIX wdt: <http://www.wikidata.org/prop/direct/> \n"
                + "PREFIX wikibase: <http://wikiba.se/ontology#> \n"
                + " \n"
                + "SELECT DISTINCT ?item ?itemLabel ?awardLabel ?time \n"
                + "{ \n"
                + "  # Items with the Occupation(P 106) of Director(Q 3455803) or a subclass(P 279) \n"
                + "    ?item wdt:P106/wdt:P279* wd:Q33999 ; \n"
                + "          # ... with an awarded(P 166) statement \n"
                + "          p:P166 ?awardStat . \n"
                + "  # Get the award (which is \"subject of\" XXth Academy Awards) \n"
                + "    ?awardStat pq:P805 ?award ; \n"
                + "               # ... that has the value Academy Award for Best Director(Q 103360) \n"
                + "               ps:P166 wd:Q103916 . \n"
                + "   # the \"point of time\" of the Academy Award \n"
                + "    ?award wdt:P585 ?time . \n"
                + "    SERVICE wikibase:label { \n"
                + "      # ... include the labels \n"
                + "        bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],en\" \n"
                + "    } \n"
                + "} \n"
                + "ORDER BY DESC(?time) \n";
        Query query = QueryFactory.create(queryBestActors);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService("https://query.wikidata.org/sparql", query)) {
            ResultSet resultSet = queryExecution.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution qs = resultSet.nextSolution();
                String item = null,
                        itemLabel = null,
                        awardLabel = null,
                        time = null;

                if (qs.contains("item") && qs.get("item") != null)
                    item = qs.getResource("item").toString();
                if (qs.contains("itemLabel") && qs.get("itemLabel") != null && qs.get("itemLabel").isLiteral())
                    itemLabel = qs.getLiteral("itemLabel").getLexicalForm();
                if (qs.contains("awardLabel") && qs.get("awardLabel") != null && qs.get("awardLabel").isLiteral())
                    awardLabel = qs.getLiteral("awardLabel").getLexicalForm();
                if (qs.contains("time") && qs.get("time") != null && qs.get("time").isLiteral())
                    time = qs.getLiteral("time").getLexicalForm();

                winner.getBestActors().add(new WinnersDTO(item, itemLabel, awardLabel, time));
            }
        }

        return winner;
    }

    @Override
    public Winners addWinnersBestActress(Winners winner) {
        String queryBestActress = "PREFIX bd: <http://www.bigdata.com/rdf#> \n"
                + "PREFIX p: <http://www.wikidata.org/prop/> \n"
                + "PREFIX pq: <http://www.wikidata.org/prop/qualifier/> \n"
                + "PREFIX ps: <http://www.wikidata.org/prop/statement/> \n"
                + "PREFIX wd: <http://www.wikidata.org/entity/> \n"
                + "PREFIX wdt: <http://www.wikidata.org/prop/direct/> \n"
                + "PREFIX wikibase: <http://wikiba.se/ontology#> \n"
                + " \n"
                + "SELECT DISTINCT ?item ?itemLabel ?awardLabel ?time \n"
                + "{ \n"
                + "  # Items with the Occupation(P 106) of Director(Q 3455803) or a subclass(P 279) \n"
                + "    ?item wdt:P106/wdt:P279* wd:Q33999 ; \n"
                + "          # ... with an awarded(P 166) statement \n"
                + "          p:P166 ?awardStat . \n"
                + "  # Get the award (which is \"subject of\" XXth Academy Awards) \n"
                + "    ?awardStat pq:P805 ?award ; \n"
                + "               # ... that has the value Academy Award for Best Director(Q 103360) \n"
                + "               ps:P166 wd:Q103618 . \n"
                + "   # the \"point of time\" of the Academy Award \n"
                + "    ?award wdt:P585 ?time . \n"
                + "    SERVICE wikibase:label { \n"
                + "      # ... include the labels \n"
                + "        bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],en\" \n"
                + "    } \n"
                + "} \n"
                + "ORDER BY DESC(?time) \n";
        Query query = QueryFactory.create(queryBestActress);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService("https://query.wikidata.org/sparql", query)) {
            ResultSet resultSet = queryExecution.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution qs = resultSet.nextSolution();
                String item = null,
                        itemLabel = null,
                        awardLabel = null,
                        time = null;

                if (qs.contains("item") && qs.get("item") != null)
                    item = qs.getResource("item").toString();
                if (qs.contains("itemLabel") && qs.get("itemLabel") != null && qs.get("itemLabel").isLiteral())
                    itemLabel = qs.getLiteral("itemLabel").getLexicalForm();
                if (qs.contains("awardLabel") && qs.get("awardLabel") != null && qs.get("awardLabel").isLiteral())
                    awardLabel = qs.getLiteral("awardLabel").getLexicalForm();
                if (qs.contains("time") && qs.get("time") != null && qs.get("time").isLiteral())
                    time = qs.getLiteral("time").getLexicalForm();

                winner.getBestActress().add(new WinnersDTO(item, itemLabel, awardLabel, time));
            }
        }

        return winner;
    }

    @Override
    public Winners addWinnersBestSupportingActors(Winners winner) {
        String queryBestSupportingActors = "PREFIX bd: <http://www.bigdata.com/rdf#> \n"
                + "PREFIX p: <http://www.wikidata.org/prop/> \n"
                + "PREFIX pq: <http://www.wikidata.org/prop/qualifier/> \n"
                + "PREFIX ps: <http://www.wikidata.org/prop/statement/> \n"
                + "PREFIX wd: <http://www.wikidata.org/entity/> \n"
                + "PREFIX wdt: <http://www.wikidata.org/prop/direct/> \n"
                + "PREFIX wikibase: <http://wikiba.se/ontology#> \n"
                + " \n"
                + "SELECT DISTINCT ?item ?itemLabel ?awardLabel ?time \n"
                + "{ \n"
                + "  # Items with the Occupation(P 106) of Director(Q 3455803) or a subclass(P 279) \n"
                + "    ?item wdt:P106/wdt:P279* wd:Q33999 ; \n"
                + "          # ... with an awarded(P 166) statement \n"
                + "          p:P166 ?awardStat . \n"
                + "  # Get the award (which is \"subject of\" XXth Academy Awards) \n"
                + "    ?awardStat pq:P805 ?award ; \n"
                + "               # ... that has the value Academy Award for Best Director(Q 103360) \n"
                + "               ps:P166 wd:Q106291 . \n"
                + "   # the \"point of time\" of the Academy Award \n"
                + "    ?award wdt:P585 ?time . \n"
                + "    SERVICE wikibase:label { \n"
                + "      # ... include the labels \n"
                + "        bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],en\" \n"
                + "    } \n"
                + "} \n"
                + "ORDER BY DESC(?time) \n";
        Query query = QueryFactory.create(queryBestSupportingActors);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService("https://query.wikidata.org/sparql", query)) {
            ResultSet resultSet = queryExecution.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution qs = resultSet.nextSolution();
                String item = null,
                        itemLabel = null,
                        awardLabel = null,
                        time = null;

                if (qs.contains("item") && qs.get("item") != null)
                    item = qs.getResource("item").toString();
                if (qs.contains("itemLabel") && qs.get("itemLabel") != null && qs.get("itemLabel").isLiteral())
                    itemLabel = qs.getLiteral("itemLabel").getLexicalForm();
                if (qs.contains("awardLabel") && qs.get("awardLabel") != null && qs.get("awardLabel").isLiteral())
                    awardLabel = qs.getLiteral("awardLabel").getLexicalForm();
                if (qs.contains("time") && qs.get("time") != null && qs.get("time").isLiteral())
                    time = qs.getLiteral("time").getLexicalForm();

                winner.getBestSupportingActors().add(new WinnersDTO(item, itemLabel, awardLabel, time));
            }
        }

        return winner;
    }

    @Override
    public Winners addWinnersBestSupportingActress(Winners winner) {
        String queryBestSupportingActress = "PREFIX bd: <http://www.bigdata.com/rdf#> \n"
                + "PREFIX p: <http://www.wikidata.org/prop/> \n"
                + "PREFIX pq: <http://www.wikidata.org/prop/qualifier/> \n"
                + "PREFIX ps: <http://www.wikidata.org/prop/statement/> \n"
                + "PREFIX wd: <http://www.wikidata.org/entity/> \n"
                + "PREFIX wdt: <http://www.wikidata.org/prop/direct/> \n"
                + "PREFIX wikibase: <http://wikiba.se/ontology#> \n"
                + " \n"
                + "SELECT DISTINCT ?item ?itemLabel ?awardLabel ?time \n"
                + "{ \n"
                + "  # Items with the Occupation(P 106) of Director(Q 3455803) or a subclass(P 279) \n"
                + "    ?item wdt:P106/wdt:P279* wd:Q33999 ; \n"
                + "          # ... with an awarded(P 166) statement \n"
                + "          p:P166 ?awardStat . \n"
                + "  # Get the award (which is \"subject of\" XXth Academy Awards) \n"
                + "    ?awardStat pq:P805 ?award ; \n"
                + "               # ... that has the value Academy Award for Best Director(Q 103360) \n"
                + "               ps:P166 wd:Q106301 . \n"
                + "   # the \"point of time\" of the Academy Award \n"
                + "    ?award wdt:P585 ?time . \n"
                + "    SERVICE wikibase:label { \n"
                + "      # ... include the labels \n"
                + "        bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],en\" \n"
                + "    } \n"
                + "} \n"
                + "ORDER BY DESC(?time) \n";
        Query query = QueryFactory.create(queryBestSupportingActress);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService("https://query.wikidata.org/sparql", query)) {
            ResultSet resultSet = queryExecution.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution qs = resultSet.nextSolution();
                String item = null,
                        itemLabel = null,
                        awardLabel = null,
                        time = null;

                if (qs.contains("item") && qs.get("item") != null)
                    item = qs.getResource("item").toString();
                if (qs.contains("itemLabel") && qs.get("itemLabel") != null && qs.get("itemLabel").isLiteral())
                    itemLabel = qs.getLiteral("itemLabel").getLexicalForm();
                if (qs.contains("awardLabel") && qs.get("awardLabel") != null && qs.get("awardLabel").isLiteral())
                    awardLabel = qs.getLiteral("awardLabel").getLexicalForm();
                if (qs.contains("time") && qs.get("time") != null && qs.get("time").isLiteral())
                    time = qs.getLiteral("time").getLexicalForm();

                winner.getBestSupportingActress().add(new WinnersDTO(item, itemLabel, awardLabel, time));
            }
        }

        return winner;
    }

    @Override
    public Winners addWinnersBestPictures(Winners winner) {
        String queryBestPictures = "PREFIX bd: <http://www.bigdata.com/rdf#> \n"
                + "PREFIX p: <http://www.wikidata.org/prop/> \n"
                + "PREFIX pq: <http://www.wikidata.org/prop/qualifier/> \n"
                + "PREFIX ps: <http://www.wikidata.org/prop/statement/> \n"
                + "PREFIX wd: <http://www.wikidata.org/entity/> \n"
                + "PREFIX wdt: <http://www.wikidata.org/prop/direct/> \n"
                + "PREFIX wikibase: <http://wikiba.se/ontology#> \n"
                + " \n"
                + "SELECT ?item ?itemLabel ?awardLabel ?time WHERE {\n" +
                "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],en\". }\n" +
                "  ?item wdt:P166 wd:Q102427.\n" +
                "  ?item wdt:P31 wd:Q11424.\n" +
                "}";
        Query query = QueryFactory.create(queryBestPictures);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService("https://query.wikidata.org/sparql", query)) {
            ResultSet resultSet = queryExecution.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution qs = resultSet.nextSolution();
                String item = null,
                        itemLabel = null,
                        awardLabel = null,
                        time = null;

                if (qs.contains("item") && qs.get("item") != null)
                    item = qs.getResource("item").toString();
                if (qs.contains("itemLabel") && qs.get("itemLabel") != null && qs.get("itemLabel").isLiteral())
                    itemLabel = qs.getLiteral("itemLabel").getLexicalForm();
                if (qs.contains("awardLabel") && qs.get("awardLabel") != null && qs.get("awardLabel").isLiteral())
                    awardLabel = qs.getLiteral("awardLabel").getLexicalForm();
                if (qs.contains("time") && qs.get("time") != null && qs.get("time").isLiteral())
                    time = qs.getLiteral("time").getLexicalForm();

                winner.getBestPictures().add(new WinnersDTO(item, itemLabel, awardLabel, time));
            }
        }

        return winner;

    }

    @Override
    public Winners addWinnersBestScreenPlays(Winners winner) {
        String queryBestScreenPlays = "PREFIX bd: <http://www.bigdata.com/rdf#> \n"
                + "PREFIX p: <http://www.wikidata.org/prop/> \n"
                + "PREFIX pq: <http://www.wikidata.org/prop/qualifier/> \n"
                + "PREFIX ps: <http://www.wikidata.org/prop/statement/> \n"
                + "PREFIX wd: <http://www.wikidata.org/entity/> \n"
                + "PREFIX wdt: <http://www.wikidata.org/prop/direct/> \n"
                + "PREFIX wikibase: <http://wikiba.se/ontology#> \n"
                + " \n"
                +"SELECT ?item ?itemLabel WHERE {\n" +
                "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],en\". }\n" +
                "  ?item wdt:P166 wd:Q41417;\n" +
                "    wdt:P31 wd:Q11424.\n" +
                "}";
        Query query = QueryFactory.create(queryBestScreenPlays);

        try (QueryExecution queryExecution = QueryExecutionFactory.sparqlService("https://query.wikidata.org/sparql", query)) {
            ResultSet resultSet = queryExecution.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution qs = resultSet.nextSolution();
                String item = null,
                        itemLabel = null,
                        awardLabel = null,
                        time = null;

                if (qs.contains("item") && qs.get("item") != null)
                    item = qs.getResource("item").toString();
                if (qs.contains("itemLabel") && qs.get("itemLabel") != null && qs.get("itemLabel").isLiteral())
                    itemLabel = qs.getLiteral("itemLabel").getLexicalForm();
                if (qs.contains("awardLabel") && qs.get("awardLabel") != null && qs.get("awardLabel").isLiteral())
                    awardLabel = qs.getLiteral("awardLabel").getLexicalForm();
                if (qs.contains("time") && qs.get("time") != null && qs.get("time").isLiteral())
                    time = qs.getLiteral("time").getLexicalForm();

                winner.getBestScreenPlays().add(new WinnersDTO(item, itemLabel, awardLabel, time));
            }
        }

        return winner;


    }


}





