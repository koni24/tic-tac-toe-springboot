package com.projects.tic_tac_toe.service;

import com.projects.tic_tac_toe.dto.GameRequestDTO;
import com.projects.tic_tac_toe.dto.MoveDTO;
import com.projects.tic_tac_toe.entity.Game;
import com.projects.tic_tac_toe.entity.User;
import com.projects.tic_tac_toe.repository.GameRepository;
import com.projects.tic_tac_toe.repository.UserRepository;
import com.projects.tic_tac_toe.util.TicTacToeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Game makeMove(Long gameId, MoveDTO move) throws Exception {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        if (!game.getStatus().equals("IN_PROGRESS")) {
            throw new RuntimeException("Game already finished");
        }

        List<Map<String, Object>> moves = TicTacToeUtils.parseMovesJson(game.getMovesJson());
        String currentSymbol = moves.size() % 2 == 0 ? "X" : "O";

        // Update board
        String[][] board = TicTacToeUtils.getBoardFromMoves(moves);
        if (!board[move.getRow()][move.getCol()].equals("")) {
            throw new RuntimeException("Cell already occupied");
        }

        // Add move
        Map<String, Object> newMove = new HashMap<>();
        newMove.put("row", move.getRow());
        newMove.put("col", move.getCol());
        newMove.put("player", currentSymbol);
        moves.add(newMove);

        // Update game status
        board = TicTacToeUtils.getBoardFromMoves(moves);

        if (TicTacToeUtils.isWinning(board, currentSymbol)) {
            game.setStatus("WIN");
            game.setWinner(currentSymbol.equals("X") ? "PLAYER1" : "PLAYER2");
        } else if (TicTacToeUtils.isBoardFull(board)) {
            game.setStatus("DRAW");
            game.setWinner("DRAW");
        }

        game.setMovesJson(TicTacToeUtils.movesToJson(moves));
        return gameRepository.save(game);
    }

}
