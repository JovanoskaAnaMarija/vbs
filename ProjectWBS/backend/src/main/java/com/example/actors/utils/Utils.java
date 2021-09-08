package com.example.actors.utils;



import com.example.actors.model.*;
import com.example.actors.model.dto.ActorAwardDTO;
import com.example.actors.model.dto.ActorMovieDTO;
import com.example.actors.model.dto.WinnersDTO;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;


import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {

    public static void addActorBaseInfo(Actor actor, QuerySolution qs) {
        actor.setURI(qs.contains("actor") && qs.get("actor").isResource() ?
                qs.getResource("actor").toString().split("/")[qs.getResource("actor").toString().split("/").length - 1]
                : null);
        actor.setName(qs.get("name") != null && qs.get("name").isLiteral() ? qs.get("name").asLiteral().getLexicalForm() : null);
        actor.setFullName(qs.get("fullName") != null && qs.get("fullName").isLiteral() ? qs.get("fullName").asLiteral().getLexicalForm() : null);
        actor.setThumbnail(qs.get("thumbnail") != null ? qs.get("thumbnail").toString() : null);
        actor.setDescription(qs.get("abstract") != null && qs.get("abstract").isLiteral() ? qs.get("abstract").asLiteral().getLexicalForm() : null);
        actor.setComment(qs.get("comment") != null && qs.get("comment").isLiteral() ? qs.get("comment").asLiteral().getLexicalForm() : null);
        actor.setYearsActive(qs.get("yearsActive") != null && qs.get("yearsActive").isLiteral() ? qs.get("yearsActive").asLiteral().getLexicalForm() : null);
        actor.setBirthPlace(qs.get("birthPlace") != null && qs.get("birthPlace").isLiteral() ?
                qs.get("birthPlace").asLiteral().getLexicalForm() :
                qs.get("birthPlace") != null ? qs.get("birthPlace").toString() : null);
        actor.setDeathPlace(qs.get("deathPlace") != null ? qs.get("deathPlace").toString() : null);
        try {
            actor.setBirthDate((qs.get("birthDate") != null && qs.get("birthDate").isLiteral()) ?
                    new SimpleDateFormat("yyyy-MM-dd").parse(
                            qs.get("birthDate").asLiteral().toString()
                    ) : null);
            actor.setDeathDate((qs.get("deathDate") != null && qs.get("deathDate").isLiteral()) ?
                    new SimpleDateFormat("yyyy-MM-dd").parse(
                            qs.get("deathDate").asLiteral().toString()
                    ) : null);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addActorQuotes(Actor actor, ResultSet resultSet) {
        while (resultSet.hasNext()) {
            QuerySolution qs = resultSet.nextSolution();
            actor.getActorQuotes().add(qs.get("quote").asLiteral().getLexicalForm());
        }
    }

    public static void addActorAw(Actor actor, ResultSet resultSet) {
        while (resultSet.hasNext()) {
            QuerySolution qs = resultSet.nextSolution();
            actor.getActorAw().add(qs.get("comment").asLiteral().getLexicalForm());
        }
    }

    public static void addActorMovies(Actor actor, ResultSet resultSet) {
        while (resultSet.hasNext()) {
            QuerySolution qs = resultSet.nextSolution();
            String uri = null,
                    description = null,
                    label = null;

            if (qs.contains("movie") && qs.get("movie") != null)
                uri = qs.getResource("movie").toString();
            if (qs.contains("label") && qs.get("label") != null && qs.get("label").isLiteral())
                label = qs.getLiteral("label").getLexicalForm();
            if (qs.contains("abstract") && qs.get("abstract") != null && qs.get("abstract").isLiteral())
                description = qs.getLiteral("abstract").getLexicalForm();

            actor.getMovies().add(new ActorMovieDTO(uri, label, description));
        }
    }


    public static void addMovieBaseInfo(Movie movie, QuerySolution qs) {
        if (qs.contains("label") && qs.get("label") != null && qs.get("label").isLiteral())
            movie.setLabel(qs.get("label").asLiteral().getLexicalForm());
        if (qs.contains("abstract") && qs.get("abstract") != null && qs.get("abstract").isLiteral())
            movie.setDescription(qs.get("abstract").asLiteral().getLexicalForm());
        if (qs.contains("budget") && qs.get("budget") != null && qs.get("budget").isLiteral())
            movie.setBudget(qs.get("budget").asLiteral().getLexicalForm());
        if (qs.contains("cinematoGraphy") && qs.get("cinematoGraphy") != null && qs.get("cinematoGraphy").isResource())
            movie.setCinematoGraphy(qs.get("cinematoGraphy").asResource().getLocalName());

        if (qs.contains("director") && qs.get("director") != null && qs.get("director").isResource())
            movie.setDirector(qs.get("director").asResource().getLocalName());

        if (qs.contains("distributor") && qs.get("distributor") != null && qs.get("distributor").isResource())
            movie.setDistributor(qs.get("distributor").asResource().getLocalName());

        if (qs.contains("musicComposer") && qs.get("musicComposer") != null && qs.get("musicComposer").isResource())
            movie.setMusicComposer(qs.get("musicComposer").asResource().getLocalName());

        if (qs.contains("country") && qs.get("country") != null && qs.get("country").isLiteral())
            movie.setCountry(qs.get("country").asLiteral().getLexicalForm());


    }

    public static void addActorAwards(Actor actor, ResultSet resultSet) {
        while (resultSet.hasNext()) {
            QuerySolution qs = resultSet.nextSolution();
            String uri = null,
                    description = null,
                    comment = null,
                    wins=null,
                    nominations=null;

            if (qs.contains("award") && qs.get("award") != null)
                uri = qs.getResource("award").toString();
            if (qs.contains("abstract") && qs.get("abstract") != null && qs.get("abstract").isLiteral())
                description = qs.getLiteral("abstract").getLexicalForm();
            if (qs.contains("comment") && qs.get("comment") != null && qs.get("comment").isLiteral())
                comment = qs.getLiteral("comment").getLexicalForm();
            if (qs.contains("wins") && qs.get("wins") != null && qs.get("wins").isLiteral())
                wins = qs.getLiteral("wins").getLexicalForm();
            if (qs.contains("nominations") && qs.get("nominations") != null && qs.get("nominations").isLiteral())
                nominations = qs.getLiteral("nominations").getLexicalForm();

            actor.getActorAwards().add(new ActorAwardDTO(uri, description, comment, wins, nominations));
        }
    }
    public static void addOscarsBaseInfo(Oscars oscars, QuerySolution qs) {
        oscars.setLabel(qs.get("label") != null ? qs.get("label").asLiteral().getLexicalForm() : null);
        oscars.setComment(qs.get("comment") != null ? qs.get("comment").asLiteral().getLexicalForm() : null);
        oscars.setDescription(qs.get("abstract") != null ? qs.get("abstract").asLiteral().getLexicalForm() : null);
        oscars.setYear(qs.get("year") != null ? qs.get("year").asLiteral().getLexicalForm() : null);
        oscars.setCountry(qs.get("country") != null ? qs.get("country").asLiteral().getLexicalForm() : null);
        oscars.setPresenter(qs.get("presenter") != null ? qs.get("presenter").asResource().getLocalName() : null);

    }

    public static void addActorNominations(Actor actor, QuerySolution qs) {
        actor.setNominations(qs.get("nominations") != null && qs.get("nominations").isLiteral() ? qs.get("nominations").asLiteral().getLexicalForm() : null);
    }

    public static void addActorWins(Actor actor, QuerySolution qs) {
        actor.setWins(qs.get("wins") != null && qs.get("wins").isLiteral() ? qs.get("wins").asLiteral().getLexicalForm() : null);
    }

    public static void addWinnersBestDirectors(Winners winner, ResultSet resultSet) {
        while (resultSet.hasNext()) {
            QuerySolution qs = resultSet.nextSolution();
            String item = null,
                    itemLabel = null,
                    awardLabel = null,
                    time=null;

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





    public static void addAcademyAwardBaseInfo(AcademyAward academyAward, QuerySolution qs) {
        academyAward.setLabel(qs.get("label") != null ? qs.get("label").asLiteral().getLexicalForm() : null);
        academyAward.setComment(qs.get("comment") != null ? qs.get("comment").asLiteral().getLexicalForm() : null);
        academyAward.setDescription(qs.get("abstract") != null ? qs.get("abstract").asLiteral().getLexicalForm() : null);

    }
    public static void addAwardActors(AcademyAward academyAward, ResultSet resultSet) {
        while (resultSet.hasNext()) {
            QuerySolution qs = resultSet.nextSolution();
            Actor actor = new Actor();
            addActorBaseInfo(actor, qs);
            academyAward.getActors().add(actor);
        }
    }

    public static void addAwardMovies(AcademyAward academyAward, ResultSet resultSet) {
        while (resultSet.hasNext()) {
            QuerySolution qs = resultSet.nextSolution();
            Movie movie = new Movie();
            addMovieBaseInfo(movie, qs);
            academyAward.getMovies().add(movie);
        }
    }
}
