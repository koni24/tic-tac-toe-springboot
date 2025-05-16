package com.projects.tic_tac_toe.dto;

import lombok.Data;

@Data
public class GameRequestDTO {
    private String opponentUsername; // Leave null for AI
    private boolean isVsAI;
    private String aiLevel; // "EASY", "MEDIUM", "HARD"
}