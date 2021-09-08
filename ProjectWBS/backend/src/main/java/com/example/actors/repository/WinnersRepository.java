package com.example.actors.repository;

import com.example.actors.model.Winners;
import com.example.actors.model.dto.WinnersDTO;
import com.example.actors.model.exceptions.WinnerNotFoundException;

public interface WinnersRepository {
    WinnersDTO getBestDirectorsData();


    Winners addWinnersBestDirectors(Winners winner);

    Winners addWinnersBestActors(Winners winner);

    Winners addWinnersBestActress(Winners winner);


    Winners addWinnersBestSupportingActors(Winners winner);

    Winners addWinnersBestSupportingActress(Winners winner);

    Winners addWinnersBestPictures(Winners winner);

    Winners addWinnersBestScreenPlays(Winners winner);


}
