package com.aluraChaleng.Foro_Hub.dto.DtoGetData.topics;

import com.aluraChaleng.Foro_Hub.model.Course;
import com.aluraChaleng.Foro_Hub.model.User;

public record DtoCreateTopicToDatabase(
        String title,
        String message,
        User user,
        Course course
) {
}
