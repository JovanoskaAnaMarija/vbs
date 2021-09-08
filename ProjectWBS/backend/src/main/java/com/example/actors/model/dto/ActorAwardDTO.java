package com.example.actors.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActorAwardDTO {
    String URI;
    String description;
    String comment;
    String wins;
    String nominations;
}
