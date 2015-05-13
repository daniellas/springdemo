package com.recruitiva.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Category extends EntityBase {

    private static final long serialVersionUID = 1L;

    @Column(unique = true)
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
