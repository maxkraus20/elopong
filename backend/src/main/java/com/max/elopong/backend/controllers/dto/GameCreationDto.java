package com.max.elopong.backend.controllers.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GameCreationDto {

    @NotNull(message = "You must input a player 1.")
    private String player1Email;

    @NotNull(message = "You must input a player 2.")
    private String player2Email;

    private String player3Email;

    private String player4Email;

    private boolean team1Winner;

    private boolean single;

    @NotNull
    private LocalDate timestamp;
}
