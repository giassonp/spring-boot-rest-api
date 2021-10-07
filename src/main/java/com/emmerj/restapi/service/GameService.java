package com.emmerj.restapi.service;

import com.emmerj.restapi.model.Game;
import java.util.List;
import java.util.Optional;

public interface GameService {

    List<Game> getAllGames(String title);

    List<Game> getUpcomingGames();

    List<Game> getMultiplayerGames();

    List<Game> getSinglePlayerGames();

    Optional<Game> getGameById(Long id);

    void deleteGameById(Long id);

    void addGame(Game game);
}
