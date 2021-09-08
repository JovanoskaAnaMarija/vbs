package com.example.actors.controller;

import com.example.actors.model.AcademyAward;
import com.example.actors.service.AcademyAwardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/awards")
@CrossOrigin(origins = "http://localhost:3000")
public class AcademyAwardController {

    private final AcademyAwardService academyAwardService;

    public AcademyAwardController(AcademyAwardService academyAwardService) {
        this.academyAwardService = academyAwardService;
    }

    @GetMapping("/search")
    public AcademyAward getAcademyAwardInfo(@RequestParam("award") String awardURI) {
        return this.academyAwardService.getAcademyAwardInfo(awardURI);
    }
    @GetMapping("/request")
    public AcademyAward getAcademyAwardMovies(@RequestParam("film") String awardURI)
    {
        return this.academyAwardService.getAcademyAwardMovies(awardURI);
    }


}
