package com.example.actors.service;

import com.example.actors.model.AcademyAward;

public interface AcademyAwardService {

    AcademyAward getAcademyAwardInfo(String awardURI);
    AcademyAward getAcademyAwardMovies(String awardURI);
}
