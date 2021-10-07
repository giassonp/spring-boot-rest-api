package com.emmerj.restapi.service;

import com.emmerj.restapi.model.Game;
import com.emmerj.restapi.model.GameRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {


    private GameRepository gameRepository;

    public GameServiceImpl() {}

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> getAllGames(String title) {
        return StringUtils.isNotBlank(title) ? gameRepository.findAll(titleLike(title)) : gameRepository.findAll();
    }

    @Override
    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    public void deleteGameById(Long id){
        gameRepository.deleteById(id);
    }

    @Override
    public void addGame(Game game) {
        gameRepository.save(game);
    }

    // Filtering with custom repository query

    @Override
    public List<Game> getMultiplayerGames(){
        return gameRepository.findAllByMultiplayer(true);
    }

    @Override
    public List<Game> getSinglePlayerGames(){
        return gameRepository.findAllByMultiplayer(false);
    }

    // Filtering with Java stream
    @Override
    public List<Game> getUpcomingGames() {
        var games = getAllGames(null);

        return games.stream().filter(g ->
                        LocalDate.now().isBefore(LocalDate.ofInstant(g.getReleaseDate().toInstant(), ZoneId.systemDefault())))
                .collect(Collectors.toList());
    }

    private Specification<Game> titleLike(String title){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get("title"), "%"+title+"%");
    }
}
