package com.max.elopong.backend.controllers.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameDto {

    private Long id;

    private UserDto player1;

    private UserDto player2;

    private UserDto player3;

    private UserDto player4;

    private double player1EloOld;

    private double player2EloOld;

    private double player3EloOld;

    private double player4EloOld;

    private double player1EloNew;

    private double player2EloNew;

    private double player3EloNew;

    private double player4EloNew;

    private boolean team1Winner;

    private boolean single;

    private LocalDate timestamp;
}
