package com.aluraChaleng.Foro_Hub.Services;

import com.aluraChaleng.Foro_Hub.Respository.CourseRepository;
import com.aluraChaleng.Foro_Hub.Respository.ResponseRepository;
import com.aluraChaleng.Foro_Hub.Respository.TopicRepository;
import com.aluraChaleng.Foro_Hub.Respository.UserRepository;
import com.aluraChaleng.Foro_Hub.Services.Validation.IValidationTopic;
import com.aluraChaleng.Foro_Hub.dto.DtoGetData.topics.DtoCreateTopic;
import com.aluraChaleng.Foro_Hub.dto.DtoGetData.topics.DtoCreateTopicToDatabase;
import com.aluraChaleng.Foro_Hub.dto.DtoGetData.topics.DtoTopicSearchTitleAndYear;
import com.aluraChaleng.Foro_Hub.dto.DtoGetData.topics.DtoUpdateTopic;
import com.aluraChaleng.Foro_Hub.dto.DtoResponses.topics.DtoResponseDeleteTopic;
import com.aluraChaleng.Foro_Hub.dto.DtoResponses.topics.DtoResponseTopic;
import com.aluraChaleng.Foro_Hub.dto.DtoResponses.topics.DtoResposeGetDataTopic;
import com.aluraChaleng.Foro_Hub.dto.DtoResponses.user.DtoUser;
import com.aluraChaleng.Foro_Hub.model.Course;
import com.aluraChaleng.Foro_Hub.model.Responses;
import com.aluraChaleng.Foro_Hub.model.Topic;
import com.aluraChaleng.Foro_Hub.model.User;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.text.IntHashtable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceTopic
{
    @Autowired
    TopicRepository topicRepository;

    @Autowired
    ResponseRepository responsesRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;


    @Autowired
    List<IValidationTopic> validationTopics;

    public List<DtoResposeGetDataTopic> getAllDataTopic()
    {
        List<Topic> listTopics = topicRepository.findAll();
        List<DtoResposeGetDataTopic> topics = new ArrayList<>();

        for(Topic t :listTopics)
        {
            DtoResposeGetDataTopic dataTopic =fillDtoGetDataTopic(t);

            topics.add(dataTopic);
        }

        return topics;
    }

    public DtoResposeGetDataTopic createTopic(DtoCreateTopic dtoCreateTopic)
    {
        //Find the objects with the IDs
        Optional<User> userGetter = userRepository.findById(Long.valueOf(dtoCreateTopic.user()));
        Optional<Course> courseGetter = courseRepository.findById(Long.valueOf(dtoCreateTopic.course()));

        if(userGetter.isEmpty())
        {
            throw new ValidationException("The user not exist, please check the code");
        }

        if(courseGetter.isEmpty())
        {
            throw new ValidationException("The course not exist, please check the course code");
        }



        DtoCreateTopicToDatabase dtoCreateTopicToDatabase = new DtoCreateTopicToDatabase(
                dtoCreateTopic.title(),
                dtoCreateTopic.message(),
                userGetter.get(),
                courseGetter.get()

        );

        validationTopics.forEach(t -> t.checkValidation(dtoCreateTopicToDatabase));

        Topic topicData = new Topic(dtoCreateTopicToDatabase);

        topicRepository.save(topicData);

        DtoResposeGetDataTopic dataTopic =fillDtoGetDataTopic(topicData);

        return dataTopic;
    }

    public DtoResposeGetDataTopic getTopicById(Long id)
    {
        Optional<Topic> optional_Topic = topicRepository.findById(id);

        if(optional_Topic.isEmpty())
        {
            throw new ValidationException("The topic is not present, please check the code");
        }

        Topic topicData = optional_Topic.get();

        DtoResposeGetDataTopic dataTopic =fillDtoGetDataTopic(topicData);

        return dataTopic;
    }

    public DtoResposeGetDataTopic updateTopic(Long id, DtoUpdateTopic dtoUpdateTopic)
    {
        Optional<Topic> topicGetter = topicRepository.findById(id);

        if(topicGetter.isEmpty())
        {
            throw new ValidationException("The topic does not exist, please check the code");
        }

        Topic topic = topicGetter.get();

        Optional<User> userGetter = userRepository.findById(Long.valueOf(dtoUpdateTopic.user()));
        Optional<Course> courseGetter = courseRepository.findById(Long.valueOf(dtoUpdateTopic.course()));

        if(userGetter.isEmpty())
        {
            throw new ValidationException("The user does not exist");
        }

        if(courseGetter.isEmpty())
        {
            throw new ValidationException("The course does not exist");
        }


        topic.setMessage(dtoUpdateTopic.message());
        topic.setTitle(dtoUpdateTopic.title());
        topic.setAuthor(userGetter.get());
        topic.setCourse(courseGetter.get());

        DtoResposeGetDataTopic dtoResponseGetDataTopic = fillDtoGetDataTopic(topic);

        return dtoResponseGetDataTopic;
    }

    public DtoResponseDeleteTopic DeleteTopic(Long id)
    {
        DtoResponseDeleteTopic dtoResponseDeleteTopic = new DtoResponseDeleteTopic(200,
                "The record has been removed");

        topicRepository.deleteById(id);

        return dtoResponseDeleteTopic;
    }

    public List<DtoResposeGetDataTopic> findLastTenRecords()
    {
        List<Topic> listTopics = topicRepository.findLastTenRecordsByCreationDate();
        List<DtoResposeGetDataTopic> topics = new ArrayList<>();

        for(Topic t :listTopics)
        {
            DtoResposeGetDataTopic dataTopic =fillDtoGetDataTopic(t);

            topics.add(dataTopic);
        }

        return topics;
    }

    public List<DtoResposeGetDataTopic> findTopicByTitleAndYear(DtoTopicSearchTitleAndYear dtoTopicSearchTitleAndYear)
    {
        List<Topic> listTopics = topicRepository.findTopicByCourseNameAndYear(dtoTopicSearchTitleAndYear.courseName(),
                dtoTopicSearchTitleAndYear.year());
        List<DtoResposeGetDataTopic> topics = new ArrayList<>();

        for(Topic t :listTopics)
        {
            DtoResposeGetDataTopic dataTopic =fillDtoGetDataTopic(t);

            topics.add(dataTopic);
        }

        return topics;
    }

    public DtoResposeGetDataTopic fillDtoGetDataTopic(Topic topicData)
    {
        List<Responses> listResponses = responsesRepository.findByTopic(topicData);

        //Obtain the list of Responses
        List<DtoResponseTopic> listDtoResponseTopic = listResponses.stream()
                .map(r -> new DtoResponseTopic(r.getCreationdate(),
                        r.getMessage(),
                        r.getSolution(),
                        new DtoUser(r.getAuthor().getUsername(), r.getAuthor().getEmail())))
                .toList();

        DtoResposeGetDataTopic dtoResponseGetDataTopic = new DtoResposeGetDataTopic(
                topicData.getCode(),
                topicData.getTitle(),
                topicData.getMessage(),
                topicData.getCreationDate(),
                new DtoUser(topicData.getAuthor().getUsername(), topicData.getAuthor().getEmail()),
                topicData.getCourse().getName(),
                listDtoResponseTopic
        );

        return dtoResponseGetDataTopic;
    }
}
