package com.dinamohs.webpage.system.dto;

import java.io.Serializable;

public class Person implements Serializable {

    protected Integer idPerson;
    protected String firstName;
    protected String lastName;

    public Person() {

    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
