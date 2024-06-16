package com.aluraChaleng.Foro_Hub.dto.DtoGetData.topics;

public record DtoCreateResponse(
        String message,
        int idTopic,
        int idAuthor,
        String solution
) {
}
