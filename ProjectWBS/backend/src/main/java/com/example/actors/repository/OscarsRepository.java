package com.example.actors.repository;

import com.example.actors.model.Oscars;
import com.example.actors.model.dto.OscarsDTO;
import com.example.actors.model.dto.OscarsInfoDTO;

public interface OscarsRepository {

    void addOscarsBaseInfo(String oscarsURI, Oscars oscars);

    OscarsInfoDTO getOscarsHistory();


    OscarsDTO getOscarsData();
}
