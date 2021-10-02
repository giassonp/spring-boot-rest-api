package com.emmerj.apiexample.controller;

import com.emmerj.apiexample.model.Game;
import com.emmerj.apiexample.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    private GameService gameService;

    public HomeController(){};

    @Autowired
    public HomeController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping(value = "/games")
    public List<Game> getAllGames(){
        return gameService.getAllGames();
    }
}
