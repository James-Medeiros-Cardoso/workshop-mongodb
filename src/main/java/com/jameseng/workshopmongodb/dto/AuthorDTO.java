package com.jameseng.workshopmongodb.dto;

import com.jameseng.workshopmongodb.domain.User;

import java.io.Serializable;

public class AuthorDTO implements Serializable {

    private String id;
    private String name;

    public AuthorDTO() {

    }

    public AuthorDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public AuthorDTO(User user) {
        id = user.getId();
        name = user.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}