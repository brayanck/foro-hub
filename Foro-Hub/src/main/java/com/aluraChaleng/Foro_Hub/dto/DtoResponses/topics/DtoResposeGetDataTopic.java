package com.aluraChaleng.Foro_Hub.dto.DtoResponses.topics;

import com.aluraChaleng.Foro_Hub.dto.DtoResponses.user.DtoUser;

import java.time.LocalDateTime;
import java.util.List;

public record DtoResposeGetDataTopic(
        Integer id,
        String title,
        String message,
        LocalDateTime creationDate,
        DtoUser user,
        String course,
        List<DtoResponseTopic> listResponses

) {
}
