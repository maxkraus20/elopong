package com.max.elopong.backend.controllers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthRequestDto {

    @NotNull(message = "You must input an email.")
    @Email
    @NotBlank
    private String email;

    @NotNull(message = "You must input a password.")
    @Length(min = 8, max = 256)
    @NotBlank(message = "Passwort darf nicht aus Leerzeichen bestehen")
    @ToString.Exclude
    private String password;
}
