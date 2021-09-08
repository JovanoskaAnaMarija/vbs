package com.example.actors.service;

import com.example.actors.model.Winners;
import com.example.actors.model.dto.WinnersDTO;

import java.awt.event.WindowEvent;

public interface WinnersService {

    Winners getDirectors(Winners winner);
    Winners getActors(Winners winners);
    Winners getActress(Winners winners);
    Winners getSupportingActors(Winners winners);
    Winners getSupportingActress(Winners winners);

    Winners getPictures(Winners winners);
    Winners getScreenPlays(Winners winners);

    WinnersDTO getBestDirectorsData();


}
