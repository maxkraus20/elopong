package com.max.elopong.backend.controllers.dto;

import com.max.elopong.backend.types.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String email;

    private String inGameName;

    private String firstName;

    private String lastName;

    private String password;

    private Role role;

    private double eloRating;
}
