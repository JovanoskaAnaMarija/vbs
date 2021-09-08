package com.example.actors.service.impl;

import com.example.actors.model.AcademyAward;
import com.example.actors.repository.AcademyAwardRepository;
import com.example.actors.repository.AssetsRepository;
import com.example.actors.service.AcademyAwardService;
import org.springframework.stereotype.Service;

@Service
public class AcademyAwardServiceImpl implements AcademyAwardService {
    private final AcademyAwardRepository academyAwardRepository;
    private final AssetsRepository assetsRepository;

    public AcademyAwardServiceImpl(AcademyAwardRepository academyAwardRepository, AssetsRepository assetsRepository) {
        this.academyAwardRepository = academyAwardRepository;
        this.assetsRepository = assetsRepository;
    }

    @Override
    public AcademyAward getAcademyAwardInfo(String awardURI) {
        String uri = this.assetsRepository.createEntityIdentifier2(awardURI);
        AcademyAward academyAward = new AcademyAward();
        this.academyAwardRepository.addAcademyAwardBaseInfo(uri, academyAward);
        this.academyAwardRepository.addAwardActors(uri, academyAward);
//        this.academyAwardRepository.addAwardMovies(uri, academyAward);
        return academyAward;
    }

    @Override
    public AcademyAward getAcademyAwardMovies(String awardURI) {
        String uri = this.assetsRepository.createEntityIdentifier2(awardURI);
        AcademyAward academyAward = new AcademyAward();
        this.academyAwardRepository.addAwardMovies(uri, academyAward);
        return academyAward;
    }


}
