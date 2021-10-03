package com.emmerj.restapi.service;

import com.emmerj.restapi.model.Game;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface GameService {

    List<Game> getAllGames(Specification<Game> spec);

    List<Game> getUpcomingGames();

    List<Game> getMultiplayerGames();

    List<Game> getSinglePlayerGames();

    Optional<Game> getGameById(Long id);

    void deleteGameById(Long id);

    void addGame(Game game);
}
