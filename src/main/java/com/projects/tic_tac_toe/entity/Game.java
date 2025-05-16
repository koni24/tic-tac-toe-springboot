package com.projects.tic_tac_toe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "games")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Player who initiated the game
    @ManyToOne
    @JoinColumn(name = "player1_id")
    private User player1;

    // Player2 is null if playing against AI
    @ManyToOne
    @JoinColumn(name = "player2_id")
    private User player2;

    private boolean isVsAI;

    private String aiLevel; // "EASY", "MEDIUM", "HARD"

    private String status; // "IN_PROGRESS", "WIN", "DRAW"

    private String winner; // "PLAYER1", "PLAYER2", "DRAW", "NONE"

    @Column(columnDefinition = "TEXT")
    private String movesJson; // JSON like [{"row":0,"col":1,"player":"X"}, ...]

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}