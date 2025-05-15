package com.projects.tic_tac_toe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String username;
    private String email;
    private String role;
}
