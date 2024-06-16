package com.aluraChaleng.Foro_Hub.model;


import com.aluraChaleng.Foro_Hub.dto.DtoGetData.topics.DtoCreateTopicToDatabase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "topic")
@Entity
@NoArgsConstructor
public class Topic
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Code;
    private String title;
    private String message;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;


    @JoinColumn(name="author", referencedColumnName="code")
    @OneToOne
    private User author;

    @JoinColumn(name="course", referencedColumnName="code")
    @OneToOne
    private Course course;

    public Topic(DtoCreateTopicToDatabase dtoCreateTopicToDatabase)
    {
        this.title = dtoCreateTopicToDatabase.title();
        this.message = dtoCreateTopicToDatabase.message();
        this.creationDate = LocalDateTime.now();
        this.author = dtoCreateTopicToDatabase.user();
        this.course = dtoCreateTopicToDatabase.course();
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
