package com.aluraChaleng.Foro_Hub.dto.DtoGetData.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoCreateUserToDatabase(
        @NotBlank String username,
        @Email String email,
        @NotBlank String password,
        @NotNull Integer typeOfProfile
) {
}
