package com.projects.tic_tac_toe.controller;

import com.projects.tic_tac_toe.dto.GameRequestDTO;
import com.projects.tic_tac_toe.entity.Game;
import com.projects.tic_tac_toe.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody GameRequestDTO request) {
        Game game = gameService.createGame(request);
        return ResponseEntity.ok(game);
    }
}
