package com.max.elopong.backend.services.mapper;

import com.max.elopong.backend.controllers.dto.UserDto;
import com.max.elopong.backend.models.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userEntityToUserDto(UserEntity user);

    List<UserDto> userEntityListToUserDtoList(List<UserEntity> userEntities);
}
