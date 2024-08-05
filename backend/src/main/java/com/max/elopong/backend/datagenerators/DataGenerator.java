package com.max.elopong.backend.datagenerators;

import com.max.elopong.backend.controllers.dto.GameCreationDto;
import com.max.elopong.backend.controllers.dto.UserDto;
import com.max.elopong.backend.models.UserEntity;
import com.max.elopong.backend.repositories.UserRepository;
import com.max.elopong.backend.services.GameService;
import com.max.elopong.backend.services.impl.EmailServiceImpl;
import com.max.elopong.backend.services.mapper.UserMapper;
import com.max.elopong.backend.types.Role;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class    DataGenerator {

    private final Logger LOGGER = LoggerFactory.getLogger(DataGenerator.class);
    private final UserRepository userRepository;
    private final GameService gameService;
    private final EmailServiceImpl senderService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final List<UserEntity> userList = new ArrayList<>();

    @PostConstruct
    public void generateData() {

        userList.add(new UserEntity (
                null, "admin@email.com", "admin1", "Admin", "Acc",
                passwordEncoder.encode("password"), Role.ADMIN, 1000.0
        ));
        userList.add(new UserEntity(
                null, "desi@email.com", "desi", "Desi", "Srna",
                passwordEncoder.encode("password"), Role.USER, 1170.0
        ));
        userList.add(new UserEntity(
                null, "kathi@email.com", "kathi", "Kathi", "Scheidl",
                passwordEncoder.encode("password"), Role.USER, 1180.0
        ));
        userList.add(new UserEntity(
                null, "carina@email.com", "carina", "Carina", "Franschitz",
                passwordEncoder.encode("password"), Role.USER, 1140.0
        ));
        userList.add(new UserEntity(
                null, "max@email.com", "max", "Max", "Kraus",
                passwordEncoder.encode("password"), Role.USER, 1200.0
        ));

        userRepository.saveAll(userList);
        LOGGER.info("Successfully added Users");

        String desi = "desi@email.com";
        String kathi = "kathi@email.com";
        String carina = "carina@email.com";
        String max = "max@email.com";

        LocalDate date = LocalDate.of(2024, 6, 24);
        GameCreationDto game1 = new GameCreationDto(desi, kathi, carina, max, false, false, date);
        gameService.addGame(game1);
        LOGGER.info("Successfully created Game 1");

        GameCreationDto game2 = new GameCreationDto(desi, kathi, carina, max, false, false, date);
        gameService.addGame(game2);
        LOGGER.info("Successfully created Game 2");

        GameCreationDto game3 = new GameCreationDto(desi, kathi, null, null, true, true, date);
        gameService.addGame(game3);
        LOGGER.info("Successfully created Game 3");
        Long desiId = userRepository.findByEmail(desi).orElseThrow().getId();
        System.out.println(gameService.getHistory(desiId));
    }
}
