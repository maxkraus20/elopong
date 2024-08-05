package com.max.elopong.backend.controllers.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterDto {

    @NotNull(message = "You must input an email.")
    @Email
    @NotBlank(message = "Email can't be empty.")
    private String email;

    @NotNull(message = "You must input an username.")
    @Length(max = 256)
    @NotBlank(message = "User Name can't be empty.")
    private String inGameName;

    @NotNull(message = "You must input a firstname.")
    @Length(max = 256)
    @NotBlank(message = "First Name can't be empty.")
    private String firstName;

    @NotNull(message = "You must input a lastname.")
    @Length(max = 256)
    @NotBlank(message = "Last Name can't be empty.")
    private String lastName;

    @NotNull(message = "You must input a password.")
    @Length(min = 8, max = 256)
    @NotBlank(message = "Password can't be empty.")
    @ToString.Exclude
    private String password;

}
