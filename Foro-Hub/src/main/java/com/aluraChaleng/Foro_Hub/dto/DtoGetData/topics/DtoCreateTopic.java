package com.aluraChaleng.Foro_Hub.dto.DtoGetData.topics;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoCreateTopic(

        @NotBlank String title,
        @NotBlank String message,
        @NotNull Integer user,
        @NotNull Integer course
) {
}
