package com.dinamohs.webpage.system.dto;

import java.io.Serializable;

/**
 * Represents a student enrolled in the school.
 * This class is related to the database user table
 * @author Luis Enco (crealhex)
 */
public class User implements Serializable {

    /**
     * Maps to the column <code>id_user</code>.
     */
    protected Integer idUser;

    /**
     * Maps to the column <code>id_person</code>.
     */
    protected Integer idPerson;

    /**
     * Maps to the column <code>username</code>.
     */
    protected String username;

    /**
     * Maps to the column <code>password</code>.
     */
    protected String password;

    /**
     * Empty constructor
     */
    public User() {

    }

    /**
     * Gets the id of this User.
     * @return User's primary_key.
     */
    public Integer getIdUser() {
        return idUser;
    }

    /**
     * Changes the id of this User.
     * This may cause session errors and probably need to fix.
     * @param idUser Student's primary_key.
     */
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    /**
     * Gets the id of a Person.
     * @return Person's foreign_key.
     */
    public Integer getIdPerson() {
        return idPerson;
    }

    /**
     * Changes the id of a Person.
     * This is strongly related to this User, its change may be a wrong
     * decision.
     * @param idPerson Person's foreign_key.
     */
    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    /**
     * Gets the username of this User.
     * @return User's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Changes the username of this User.
     * @param username User's username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of this User.
     * Use this many times can be a unsecured way to treat a User.
     * @return User's plain text password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Changes the password of this User.
     * This only works with plain text password for current version.
     * @param password User's plain text password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
