package com.projects.tic_tac_toe.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
