package com.projects.tic_tac_toe.service;

import com.projects.tic_tac_toe.dto.GameRequestDTO;
import com.projects.tic_tac_toe.entity.Game;
import com.projects.tic_tac_toe.entity.User;
import com.projects.tic_tac_toe.repository.GameRepository;
import com.projects.tic_tac_toe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public Game createGame(GameRequestDTO request) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User player1 = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        User player2 = null;
        if (!request.isVsAI() && request.getOpponentUsername() != null) {
            player2 = userRepository.findByUsername(request.getOpponentUsername())
                    .orElseThrow(() -> new RuntimeException("Opponent not found"));
        }

        Game game = Game.builder()
                .player1(player1)
                .player2(player2)
                .isVsAI(request.isVsAI())
                .aiLevel(request.getAiLevel())
                .status("IN_PROGRESS")
                .winner("NONE")
                .movesJson("[]")
                .build();

        return gameRepository.save(game);
    }
}
