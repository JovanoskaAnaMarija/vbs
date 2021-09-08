package com.example.actors.service;

import com.example.actors.model.Oscars;
import com.example.actors.model.dto.OscarsDTO;
import com.example.actors.model.dto.OscarsInfoDTO;

public interface OscarsService {

    Oscars getOscarsInfo(String oscarsURI);

    OscarsInfoDTO getOscarsHistory();

    OscarsDTO getOscarsData();

}

