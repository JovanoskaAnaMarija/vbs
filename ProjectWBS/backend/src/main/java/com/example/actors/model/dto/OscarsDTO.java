package com.example.actors.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OscarsDTO {
    String label;
    String comment;
    String description;
    String year;
    String country;
    String presenter;
}
