package com.aluraChaleng.Foro_Hub.dto.DtoGetData.topics;

import com.aluraChaleng.Foro_Hub.model.Topic;
import com.aluraChaleng.Foro_Hub.model.User;

public record DtoCreateResponseToDatabase(
        String message,
        Topic topic,
        User author,
        String solution
) {
}
