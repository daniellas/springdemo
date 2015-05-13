package com.recruitiva.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Category extends EntityBase {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 3, max = 255)
    @Column(unique = true)
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
