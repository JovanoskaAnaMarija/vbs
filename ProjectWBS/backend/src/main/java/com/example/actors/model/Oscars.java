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
public class Oscars {
    String label;
    String comment;
    String description;
    String year;
    String country;
    String presenter;


   // Set<Actor> actors = new TreeSet<Actor>();
}
