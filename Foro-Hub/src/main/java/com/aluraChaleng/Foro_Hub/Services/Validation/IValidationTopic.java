package com.aluraChaleng.Foro_Hub.Services.Validation;

import com.aluraChaleng.Foro_Hub.dto.DtoGetData.topics.DtoCreateTopicToDatabase;

public interface IValidationTopic {
    public void checkValidation(DtoCreateTopicToDatabase dataTopic);
}
