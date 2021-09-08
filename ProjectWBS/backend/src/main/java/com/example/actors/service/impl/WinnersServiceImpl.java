package com.example.actors.service.impl;

import com.example.actors.model.Winners;
import com.example.actors.model.dto.WinnersDTO;
import com.example.actors.repository.AssetsRepository;
import com.example.actors.repository.WinnersRepository;
import com.example.actors.service.WinnersService;
import org.springframework.stereotype.Service;

@Service
public class WinnersServiceImpl  implements WinnersService {

    private final WinnersRepository winnersRepository;
    private final AssetsRepository assetsRepository;

    public WinnersServiceImpl(WinnersRepository winnersRepository, AssetsRepository assetsRepository) {
        this.winnersRepository = winnersRepository;
        this.assetsRepository = assetsRepository;
    }


    @Override
    public Winners getDirectors(Winners winner) {
         return this.winnersRepository.addWinnersBestDirectors(winner);
    }

    @Override
    public Winners getActors(Winners winners) {
        return this.winnersRepository.addWinnersBestActors(winners);
    }

    @Override
    public Winners getActress(Winners winners) {
        return  this.winnersRepository.addWinnersBestActress(winners);
    }

    @Override
    public Winners getSupportingActors(Winners winners) {
        return this.winnersRepository.addWinnersBestSupportingActors(winners);
    }

    @Override
    public Winners getSupportingActress(Winners winners) {
        return this.winnersRepository.addWinnersBestSupportingActress(winners);
    }

    @Override
    public Winners getPictures(Winners winners) {
        return this.winnersRepository.addWinnersBestPictures(winners);
    }

    @Override
    public Winners getScreenPlays(Winners winners) {
        return this.winnersRepository.addWinnersBestScreenPlays(winners);
    }

    @Override
    public WinnersDTO getBestDirectorsData() {
        return this.winnersRepository.getBestDirectorsData();
    }
}
