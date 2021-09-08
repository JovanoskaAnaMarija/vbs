package com.example.actors.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.TreeSet;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AcademyAward {
    String label;
    String comment;
    String description;



    Set<Actor> actors = new TreeSet<Actor>();
    Set<Movie> movies = new TreeSet<Movie>();
}
