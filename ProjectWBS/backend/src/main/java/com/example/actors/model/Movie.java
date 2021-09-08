package com.example.actors.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Movie implements Comparable<Movie> {
    String label;
    String description;
    String budget;
    String cinematoGraphy;
    String director;
    String distributor;
    String musicComposer;
    String country;


    List<String> producers = new ArrayList<>();
    List<String> productionCompanies = new ArrayList<>();
    Set<Actor> actors = new TreeSet<>();

    @Override
    public int compareTo(Movie o) {
        if(this.label != null ) {
            return -1;
        }

        if(this.description != null && o.description != null) {
            return this.description.compareTo(o.description);
        }
        return 1;
    }




}
