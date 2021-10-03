package com.emmerj.restapi.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT g FROM Game g WHERE g.multiplayer = ?1")
    public List<Game> findAllByMultiplayer(boolean multiplayerInd);
}
