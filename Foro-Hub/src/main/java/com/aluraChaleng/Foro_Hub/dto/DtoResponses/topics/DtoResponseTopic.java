package com.aluraChaleng.Foro_Hub.dto.DtoResponses.topics;

import com.aluraChaleng.Foro_Hub.dto.DtoResponses.user.DtoUser;

import java.time.LocalDateTime;

public record DtoResponseTopic(
        LocalDateTime creationDate,
        String message,
        String solution,
        DtoUser author
) {
}
