package com.max.elopong.backend.services.impl;

import com.max.elopong.backend.controllers.dto.UserDto;
import com.max.elopong.backend.repositories.UserRepository;
import com.max.elopong.backend.services.UserService;
import com.max.elopong.backend.services.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<List<UserDto>> findAllUsers() {
        List<UserDto> userDtos = userMapper.userEntityListToUserDtoList(userRepository.findAll());
        userDtos.sort((Comparator.comparing(UserDto::getEloRating).reversed()));
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }
}
