package com.example.actors.controller;


import com.example.actors.model.Winners;
import com.example.actors.model.dto.WinnersDTO;
import com.example.actors.model.exceptions.WinnerNotFoundException;
import com.example.actors.service.WinnersService;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/winners")
@CrossOrigin(origins = "http://localhost:3000")
public class WinnersController {

    private final WinnersService winnersService;

    public WinnersController(WinnersService winnersService) {
        this.winnersService = winnersService;
    }


    @GetMapping("/bestDirectors")
    public Winners getWinnersData(Winners winners)
    {
        return this.winnersService.getDirectors(winners);
    }
    @GetMapping("/bestActors")
    public Winners getActorsData(Winners winners)
    {
        return this.winnersService.getActors(winners);
    }
    @GetMapping("/bestActress")
    public Winners getActressData(Winners winners)
    {
        return this.winnersService.getActress(winners);
    }


    @GetMapping("/bestSupActress")
    public Winners getSupportingActressData(Winners winners)
    {
        return this.winnersService.getSupportingActress(winners);
    }



    @GetMapping("/bestSupActors")
    public Winners getSupportingActorsData(Winners winners)
    {
        return this.winnersService.getSupportingActors(winners);
    }

    @GetMapping("/bestPictures")
    public Winners getPicturesData(Winners winners){
        return  this.winnersService.getPictures(winners);
    }
    @GetMapping("/bestScreenplays")
    public Winners getScreenPlaysData(Winners winners){
        return  this.winnersService.getScreenPlays(winners);
    }





}

