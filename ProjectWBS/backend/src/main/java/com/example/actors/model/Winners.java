package com.example.actors.model;

import com.example.actors.model.dto.WinnersDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Winners   {

    List<WinnersDTO> bestDirectors = new ArrayList<>();
    List<WinnersDTO> bestActors = new ArrayList<>();
    List<WinnersDTO> bestActress = new ArrayList<>();
    List<WinnersDTO> bestSupportingActors = new ArrayList<>();
    List<WinnersDTO> bestSupportingActress = new ArrayList<>();

    List<WinnersDTO> bestPictures = new ArrayList<>();

    List<WinnersDTO> bestScreenPlays =new ArrayList<>();

}
