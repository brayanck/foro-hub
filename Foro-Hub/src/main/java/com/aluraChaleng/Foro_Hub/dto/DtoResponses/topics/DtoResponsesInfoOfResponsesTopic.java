package com.aluraChaleng.Foro_Hub.dto.DtoResponses.topics;

import java.time.LocalDateTime;

public record DtoResponsesInfoOfResponsesTopic(
        Integer codeTopic,
        String messageTopic,
        Integer codeResponse,
        String messageResponse,
        String solution,
        LocalDateTime creationDate,
        String usernameAuthor
){
}
