package com.emmerj.restapi.controller;

import com.emmerj.restapi.model.Game;
import com.emmerj.restapi.service.GameService;
import com.mathianasj.spring.rsql.CustomRsqlVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private GameService gameService;

    public GameController(){};

    @Autowired
    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping()
    public List<Game> getAllGames(@RequestParam(value = "search", required = false) String search){
        return search != null ? gameService.getAllGames(getGameSpecification(search)) : gameService.getAllGames(null);
    }

    @GetMapping(value = "/{id}")
    public Game getGameById(@PathVariable("id") Long gameId){
        return gameService.getGameById(gameId).get();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteGameById(@PathVariable("id") Long gameId){
        gameService.deleteGameById(gameId);
    }

    @GetMapping(value = "/upcoming")
    public List<Game> getUpcomingGames(){
        return gameService.getUpcomingGames();
    }

    @GetMapping(value = "/singleplayer")
    public List<Game> getSinglePlayerGames(){
        return gameService.getSinglePlayerGames();
    }

    @GetMapping(value = "/multiplayer")
    public List<Game> getMultiplayerGames(){
        return gameService.getMultiplayerGames();
    }

    @PutMapping
    public void addGame(@RequestBody Game game){
        gameService.addGame(game);
    }

    private Specification<Game> getGameSpecification(String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<Game> spec = rootNode.accept(new CustomRsqlVisitor<Game>());
        return spec;
    }
}
