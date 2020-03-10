package com.dinamohs.webpage.system.dao;

import java.sql.ResultSet;

public abstract class DAO {

    public byte[] getBlobColumn(ResultSet result, int column) {
        return null;
    }

}
