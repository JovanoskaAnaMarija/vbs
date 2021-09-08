package com.example.actors.repository;

import com.example.actors.model.AcademyAward;

public interface AcademyAwardRepository {

    void addAcademyAwardBaseInfo(String awardURI, AcademyAward academyAward);

    void addAwardActors(String awardURI, AcademyAward academyAward);

    void addAwardMovies(String awardURI, AcademyAward academyAward);


}
