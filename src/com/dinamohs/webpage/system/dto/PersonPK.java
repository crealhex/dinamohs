package com.dinamohs.webpage.system.dto;

import java.io.Serializable;

public class PersonPK implements Serializable {

    private Integer id;

    public PersonPK() {}

    public PersonPK(final Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
