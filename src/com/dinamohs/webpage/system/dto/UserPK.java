package com.dinamohs.webpage.system.dto;

import java.io.Serializable;

public class UserPK implements Serializable {

    protected Integer idUser;

    public UserPK() {

    }

    public UserPK(final Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}
