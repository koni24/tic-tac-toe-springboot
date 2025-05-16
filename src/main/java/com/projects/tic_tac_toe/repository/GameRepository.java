package com.projects.tic_tac_toe.repository;

import com.projects.tic_tac_toe.entity.Game;
import com.projects.tic_tac_toe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByPlayer1OrPlayer2(User player1, User player2);
}
