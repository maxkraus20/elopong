package com.max.elopong.backend.services.mapper;

import com.max.elopong.backend.controllers.dto.RegisterDto;
import com.max.elopong.backend.controllers.dto.UserDto;
import com.max.elopong.backend.models.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    UserEntity registerDtoToUserEntity(RegisterDto registerDto);
}
