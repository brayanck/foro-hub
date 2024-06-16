package com.aluraChaleng.Foro_Hub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "course")
@Entity
@NoArgsConstructor
public class Course
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;

    private String name;
    private String category;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}