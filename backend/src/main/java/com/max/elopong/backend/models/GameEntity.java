package com.max.elopong.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "games")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "player1_id")
    private UserEntity player1;

    @ManyToOne
    @JoinColumn(nullable = false, name = "player2_id")
    private UserEntity player2;

    @ManyToOne
    @JoinColumn(name = "player3_id")
    private UserEntity player3;

    @ManyToOne
    @JoinColumn(name = "player4_id")
    private UserEntity player4;

    @Column
    private double player1EloOld;
    @Column
    private double player2EloOld;
    @Column
    private double player3EloOld;
    @Column
    private double player4EloOld;
    @Column
    private double player1EloNew;
    @Column
    private double player2EloNew;
    @Column
    private double player3EloNew;
    @Column
    private double player4EloNew;

    @Column(nullable = false)
    private boolean team1Winner;

    @Column(nullable = false)
    private boolean single;

    @Column(nullable = false)
    private LocalDate timestamp;
}
