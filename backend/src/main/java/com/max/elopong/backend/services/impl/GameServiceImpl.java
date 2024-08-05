package com.max.elopong.backend.services.impl;

import com.max.elopong.backend.controllers.dto.GameCreationDto;
import com.max.elopong.backend.controllers.dto.GameDto;
import com.max.elopong.backend.models.GameEntity;
import com.max.elopong.backend.models.UserEntity;
import com.max.elopong.backend.repositories.GameRepository;
import com.max.elopong.backend.repositories.UserRepository;
import com.max.elopong.backend.services.GameService;
import com.max.elopong.backend.services.mapper.GameMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    private static final int K_FACTOR = 30;

    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final GameMapper gameMapper;


    @Override
    public ResponseEntity<GameDto> addGame(GameCreationDto gameCreationDto) {
        GameEntity gameEntity = gameMapper.gameCreationDtoToGameEntity(gameCreationDto);
        gameEntity.setPlayer1(userRepository.findByEmail(gameCreationDto.getPlayer1Email()).orElseThrow());
        gameEntity.setPlayer2(userRepository.findByEmail(gameCreationDto.getPlayer2Email()).orElseThrow());

        if (!gameCreationDto.isSingle()) {
            gameEntity.setPlayer3(userRepository.findByEmail(gameCreationDto.getPlayer3Email()).orElseThrow());
            gameEntity.setPlayer4(userRepository.findByEmail(gameCreationDto.getPlayer4Email()).orElseThrow());
        } else {
            gameEntity.setPlayer3(null);
            gameEntity.setPlayer4(null);
        }

        updateEloRatings(gameEntity);

        GameDto gameDto = gameMapper.gameEntityToGameDto(gameEntity);
        return new ResponseEntity<>(gameDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<GameDto>> findAllGames() {
        List<GameDto> gameDtoList = gameMapper.gameEntityListToGameDtoList(gameRepository.findAll());
        return new ResponseEntity<>(gameDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<GameDto>> getHistory(Long id) {
        String email = userRepository.findById(id).orElseThrow().getEmail();
        List<GameDto> allGameDtoList = gameMapper.gameEntityListToGameDtoList(gameRepository.findAll());
        List<GameDto> history = new ArrayList<>();
        for (GameDto gameDto : allGameDtoList) {
            if (gameDto.getPlayer1().getEmail().equals(email) || gameDto.getPlayer2().getEmail().equals(email)) {
                history.add(gameDto);
            }
            if (!gameDto.isSingle()) {
                if (gameDto.getPlayer3().getEmail().equals(email) || gameDto.getPlayer4().getEmail().equals(email)) {
                    history.add(gameDto);
                }
            }
        }
        return new ResponseEntity<>(history, HttpStatus.OK);

    }

    private void updateEloRatings(GameEntity game) {
        UserEntity player1 = game.getPlayer1();
        UserEntity player2 = game.getPlayer2();

        double player1Elo = player1.getEloRating();
        double player2Elo = player2.getEloRating();

        game.setPlayer1EloOld(player1Elo);
        game.setPlayer2EloOld(player2Elo);

        if (game.isSingle()) {
            // Calculate probabilities
            double probPlayer1 = 1.0 / (1 + Math.pow(10, (player2Elo - player1Elo) / 400));
            double probPlayer2 = 1.0 / (1 + Math.pow(10, (player1Elo - player2Elo) / 400));

            // Calculate new ELO
            double player1UpdatedElo, player2UpdatedElo;

            if (game.isTeam1Winner()) {
                player1UpdatedElo = player1Elo + (K_FACTOR * (1 - probPlayer1));
                player2UpdatedElo = player2Elo + (K_FACTOR * (0 - probPlayer2));
            } else {
                player1UpdatedElo = player1Elo + (K_FACTOR * (0 - probPlayer1));
                player2UpdatedElo = player2Elo + (K_FACTOR * (1 - probPlayer2));
            }

            player1.setEloRating(player1UpdatedElo);
            player2.setEloRating(player2UpdatedElo);

            game.setPlayer3EloOld(0);
            game.setPlayer4EloOld(0);

            game.setPlayer1EloNew(player1UpdatedElo);
            game.setPlayer2EloNew(player2UpdatedElo);
            game.setPlayer3EloNew(0);
            game.setPlayer4EloNew(0);

            userRepository.save(player1);
            userRepository.save(player2);

            gameRepository.save(game);

        } else {
            UserEntity player3 = game.getPlayer3();
            UserEntity player4 = game.getPlayer4();

            double player3Elo = player3.getEloRating();
            double player4Elo = player4.getEloRating();

            game.setPlayer3EloOld(player4Elo);
            game.setPlayer4EloOld(player4Elo);

            // player 1 & 2 vs player 3 & 4
            double team1Elo = (player1Elo + player2Elo) / 2;
            double team2Elo = (player3Elo + player4Elo) / 2;

            // Calculate probabilities
            double probTeam1 = 1.0 / (1 + Math.pow(10, (team2Elo -team1Elo) / 400));
            double probTeam2 = 1.0 / (1 + Math.pow(10, (team1Elo - team2Elo) / 400));

            // Calculate new ELO
            double player1UpdatedElo, player2UpdatedElo, player3UpdatedElo, player4UpdatedElo;

            if (game.isTeam1Winner()) {
                player1UpdatedElo =  player1Elo + (K_FACTOR * (1 - probTeam1));
                player2UpdatedElo =  player2Elo + (K_FACTOR * (1 - probTeam1));
                player3UpdatedElo =  player3Elo + (K_FACTOR * (0 - probTeam2));
                player4UpdatedElo =  player4Elo + (K_FACTOR * (0 - probTeam2));
            } else {
                player1UpdatedElo =  player1Elo + (K_FACTOR * (0 - probTeam1));
                player2UpdatedElo =  player2Elo + (K_FACTOR * (0 - probTeam1));
                player3UpdatedElo =  player3Elo + (K_FACTOR * (1 - probTeam2));
                player4UpdatedElo =  player4Elo + (K_FACTOR * (1 - probTeam2));
            }

            player1.setEloRating(player1UpdatedElo);
            player2.setEloRating(player2UpdatedElo);
            player3.setEloRating(player3UpdatedElo);
            player4.setEloRating(player4UpdatedElo);

            game.setPlayer1EloNew(player1UpdatedElo);
            game.setPlayer2EloNew(player2UpdatedElo);
            game.setPlayer3EloNew(player3UpdatedElo);
            game.setPlayer4EloNew(player4UpdatedElo);

            userRepository.save(player1);
            userRepository.save(player2);
            userRepository.save(player3);
            userRepository.save(player4);

            gameRepository.save(game);
        }
    }
}
