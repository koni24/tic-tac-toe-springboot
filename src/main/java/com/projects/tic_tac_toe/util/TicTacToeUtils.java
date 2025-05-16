package com.projects.tic_tac_toe.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class TicTacToeUtils {

    public static final int SIZE = 3;

    public static String[][] getBoardFromMoves(List<Map<String, Object>> moves) {
        String[][] board = new String[SIZE][SIZE];
        for (String[] row : board) Arrays.fill(row, "");

        for (Map<String, Object> move : moves) {
            int row = (int) move.get("row");
            int col = (int) move.get("col");
            String player = (String) move.get("player");
            board[row][col] = player;
        }

        return board;
    }

    public static boolean isWinning(String[][] board, String symbol) {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0].equals(symbol) && board[i][1].equals(symbol) && board[i][2].equals(symbol)) return true;
            if (board[0][i].equals(symbol) && board[1][i].equals(symbol) && board[2][i].equals(symbol)) return true;
        }
        return (board[0][0].equals(symbol) && board[1][1].equals(symbol) && board[2][2].equals(symbol)) ||
                (board[0][2].equals(symbol) && board[1][1].equals(symbol) && board[2][0].equals(symbol));
    }

    public static boolean isBoardFull(String[][] board) {
        for (String[] row : board)
            for (String cell : row)
                if (cell.equals("")) return false;
        return true;
    }

    public static List<Map<String, Object>> parseMovesJson(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<List<Map<String, Object>>>() {});
    }

    public static String movesToJson(List<Map<String, Object>> moves) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(moves);
    }
}

