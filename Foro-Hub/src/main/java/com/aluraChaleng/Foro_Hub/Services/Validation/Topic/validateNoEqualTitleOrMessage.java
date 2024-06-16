package com.aluraChaleng.Foro_Hub.Services.Validation.Topic;

import com.aluraChaleng.Foro_Hub.Respository.TopicRepository;
import com.aluraChaleng.Foro_Hub.Services.Validation.IValidationTopic;
import com.aluraChaleng.Foro_Hub.dto.DtoGetData.topics.DtoCreateTopicToDatabase;
import com.aluraChaleng.Foro_Hub.model.Topic;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class validateNoEqualTitleOrMessage implements IValidationTopic {
    @Autowired
    TopicRepository topicRepository;

    @Override
    public void checkValidation(DtoCreateTopicToDatabase dataTopic)
    {
        Topic topicData = topicRepository.findTopicByTitleOrMessage(dataTopic.title(), dataTopic.message());

        if(topicData != null)
        {
            try {
                throw new ValidationException("The topic exists with title or message description");
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        }
    }
}