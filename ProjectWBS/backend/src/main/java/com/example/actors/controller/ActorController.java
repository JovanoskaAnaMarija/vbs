package com.example.actors.controller;

import com.bordercloud.sparql.SparqlClient;
import com.bordercloud.sparql.SparqlClientException;
import com.bordercloud.sparql.SparqlResult;
import com.bordercloud.sparql.SparqlResultModel;
import com.example.actors.model.Actor;
import com.example.actors.model.exceptions.ActorNotFoundException;
import com.example.actors.service.ActorService;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/api/actors")
@CrossOrigin(origins = "http://localhost:3000")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/search")
    public Actor getActorInfo(@RequestParam(name = "actor", required = false) String actor,
                              @RequestParam(name = "uri", required = false) String uri) throws ActorNotFoundException {
        if(uri != null) {
            return actorService.getActorInfoWithURI(uri);
        }
        return actorService.getActorInfo(WordUtils.capitalizeFully(actor));
    }


    @RequestMapping(method =RequestMethod.GET, value = "/directorWinners")
    public SparqlResultModel getDirectorWinners() {
        try {
            URI endpoint = new URI("https://query.wikidata.org/sparql");
            String querySelect = "PREFIX bd: <http://www.bigdata.com/rdf#> \n"
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
            SparqlClient sc = new SparqlClient(false);
            sc.setEndpointRead(endpoint);
            SparqlResult sr = sc.query(querySelect);
            //sc.printLastQueryAndResult();

            return sr.getModel();
        } catch (URISyntaxException | SparqlClientException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/bestActors")
    public SparqlResultModel getBestActorWinnersSorted() {
        try {
            URI endpoint = new URI("https://query.wikidata.org/sparql");
            String queryBestActor = "PREFIX bd: <http://www.bigdata.com/rdf#> \n"
                    + "PREFIX p: <http://www.wikidata.org/prop/> \n"
                    + "PREFIX pq: <http://www.wikidata.org/prop/qualifier/> \n"
                    + "PREFIX ps: <http://www.wikidata.org/prop/statement/> \n"
                    + "PREFIX wd: <http://www.wikidata.org/entity/> \n"
                    + "PREFIX wdt: <http://www.wikidata.org/prop/direct/> \n"
                    + "PREFIX wikibase: <http://wikiba.se/ontology#> \n"
                    + " \n" +
            "SELECT DISTINCT ?item ?itemLabel ?awardLabel ?time WHERE {"+
  "?item (wdt:P106/(wdt:P279*)) wd:Q33999;"+
                "p:P166 ?awardStat."+
                        "?awardStat pq:P805 ?award;"+
                "ps:P166 wd:Q103916."+
                       " ?award wdt:P585 ?time."+
                        "SERVICE wikibase:label { bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],en\". }"+
            "}"+
            "ORDER BY DESC (?time)";
            SparqlClient sc = new SparqlClient(false);
            sc.setEndpointRead(endpoint);
            SparqlResult sr = sc.query(queryBestActor);
            //sc.printLastQueryAndResult();

            return sr.getModel();
        } catch (URISyntaxException | SparqlClientException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/allBestActors")
    public SparqlResultModel getBestActorWinners()
    {
        try {
            URI endpoint = new URI("https://query.wikidata.org/sparql");
            String queryBestActor = "PREFIX bd: <http://www.bigdata.com/rdf#> \n"
                    + "PREFIX p: <http://www.wikidata.org/prop/> \n"
                    + "PREFIX pq: <http://www.wikidata.org/prop/qualifier/> \n"
                    + "PREFIX ps: <http://www.wikidata.org/prop/statement/> \n"
                    + "PREFIX wd: <http://www.wikidata.org/entity/> \n"
                    + "PREFIX wdt: <http://www.wikidata.org/prop/direct/> \n"
                    + "PREFIX wikibase: <http://wikiba.se/ontology#> \n"
                    + " \n" +
                    "SELECT ?Academy_Award_for_Best_Actor ?Academy_Award_for_Best_ActorLabel WHERE {"+
                "SERVICE wikibase:label { bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],en\". }"+
  "?Academy_Award_for_Best_Actor wdt:P1411 wd:Q103916."+
            "}";
            SparqlClient sc = new SparqlClient(false);
            sc.setEndpointRead(endpoint);
            SparqlResult sr = sc.query(queryBestActor);
            //sc.printLastQueryAndResult();

            return sr.getModel();
        } catch (URISyntaxException | SparqlClientException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return null;
    }


    }







