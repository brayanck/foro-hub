package com.aluraChaleng.Foro_Hub.dto.DtoGetData.topics;

import com.aluraChaleng.Foro_Hub.model.Course;
import com.aluraChaleng.Foro_Hub.model.User;

import java.time.LocalDateTime;

public record DtoCreateTopicData(
        String title,
        String message,
        LocalDateTime creationdate,
        User author,
        Course course
) {
}
