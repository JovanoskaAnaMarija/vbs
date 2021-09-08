package com.example.actors.service.impl;

import com.example.actors.model.Oscars;
import com.example.actors.model.dto.OscarsDTO;
import com.example.actors.model.dto.OscarsInfoDTO;
import com.example.actors.repository.AssetsRepository;
import com.example.actors.repository.OscarsRepository;
import com.example.actors.service.OscarsService;
import org.springframework.stereotype.Service;

@Service
public class OscarsServiceImpl implements OscarsService {
    private final OscarsRepository oscarsRepository;
    private final AssetsRepository assetsRepository;

    public OscarsServiceImpl(OscarsRepository oscarsRepository, AssetsRepository assetsRepository) {
        this.oscarsRepository = oscarsRepository;
        this.assetsRepository = assetsRepository;
    }

    @Override
    public Oscars getOscarsInfo(String oscarsURI) {
        Oscars oscars = new Oscars();
        this.oscarsRepository.addOscarsBaseInfo(oscarsURI, oscars);
        return oscars;
    }

    @Override
    public OscarsInfoDTO getOscarsHistory() {
        return this.oscarsRepository.getOscarsHistory();
    }

    @Override
    public OscarsDTO getOscarsData() {
        return this.oscarsRepository.getOscarsData();
    }
}
