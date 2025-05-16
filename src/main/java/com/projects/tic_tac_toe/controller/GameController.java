package com.projects.tic_tac_toe.controller;

import com.projects.tic_tac_toe.dto.GameRequestDTO;
import com.projects.tic_tac_toe.dto.MoveDTO;
import com.projects.tic_tac_toe.entity.Game;
import com.projects.tic_tac_toe.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{gameId}/move")
    public ResponseEntity<Game> makeMove(@PathVariable Long gameId, @RequestBody MoveDTO move) {
        try {
            Game updatedGame = gameService.makeMove(gameId, move);
            return ResponseEntity.ok(updatedGame);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
