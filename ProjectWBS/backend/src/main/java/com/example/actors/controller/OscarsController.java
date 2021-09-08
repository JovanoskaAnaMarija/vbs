package com.example.actors.controller;

import com.example.actors.model.Oscars;
import com.example.actors.model.dto.OscarsDTO;
import com.example.actors.model.dto.OscarsInfoDTO;
import com.example.actors.service.OscarsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/oscars")
@CrossOrigin(origins = "http://localhost:3000")
public class OscarsController {

    private final OscarsService oscarsService;

    public OscarsController(OscarsService oscarsService) {
        this.oscarsService = oscarsService;
    }

    @GetMapping("/search")
    public Oscars getOscarsInfo(@RequestParam("oscar") String sportURI) {
        return this.oscarsService.getOscarsInfo(sportURI);
    }

    @GetMapping("/data")
    public OscarsInfoDTO getOscarsBaseInfo() {
        return this.oscarsService.getOscarsHistory();
    }
    @GetMapping("/all")
    public OscarsDTO getOscarsData()
    {
        return this.oscarsService.getOscarsData();
    }
}

