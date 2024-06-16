package com.aluraChaleng.Foro_Hub.model;


import com.aluraChaleng.Foro_Hub.dto.DtoGetData.topics.DtoCreateResponseToDatabase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "responses")
@Entity
@NoArgsConstructor
public class Responses
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;

    private String message;

    private LocalDateTime creationdate;

    private String solution;

    @JoinColumn(name="author", referencedColumnName="code")
    @OneToOne
    private User author;

    @JoinColumn(name="topic", referencedColumnName="code")
    @OneToOne
    private Topic topic;

    public Responses(DtoCreateResponseToDatabase dtoCreateResponseToDatabase)
    {
        this.message = dtoCreateResponseToDatabase.message();
        this.creationdate = LocalDateTime.now();
        this.author = dtoCreateResponseToDatabase.author();
        this.topic = dtoCreateResponseToDatabase.topic();
        this.solution = dtoCreateResponseToDatabase.solution();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public LocalDateTime getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(LocalDateTime creationdate) {
        this.creationdate = creationdate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
