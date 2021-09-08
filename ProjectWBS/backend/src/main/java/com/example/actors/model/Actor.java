package com.example.actors.model;

import com.example.actors.model.dto.ActorAwardDTO;
import com.example.actors.model.dto.ActorMovieDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Actor implements Comparable<Actor> {
    String URI;
    String name;
    String fullName;
    String thumbnail;

    String description;
    String yearsActive;


    String comment;
    Date birthDate;
    String birthPlace;
    Date deathDate;
    String deathPlace;
    String nominations;
    String wins;


    List<String> actorQuotes = new ArrayList<>();
    List<String> actorAw = new ArrayList<>();
    List<ActorAwardDTO> actorAwards = new ArrayList<>();
    List<ActorMovieDTO> movies = new ArrayList<>();

    @Override
    public int compareTo(Actor o) {
        if(this.thumbnail != null ) {
            return -1;
        }
        if(this.fullName != null && o.fullName != null) {
            return this.fullName.compareTo(o.fullName);
        }
        if(this.name != null && o.name != null) {
            return this.name.compareTo(o.name);
        }
        if(this.description != null && o.description != null) {
            return this.description.compareTo(o.description);
        }
        return 1;
    }


}
