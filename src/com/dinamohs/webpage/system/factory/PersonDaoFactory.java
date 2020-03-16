package com.dinamohs.webpage.system.factory;

import com.dinamohs.webpage.system.dao.PersonDAO;
import com.dinamohs.webpage.system.jdbc.mysql.PersonMySQL;

public class PersonDaoFactory {

    public static PersonDAO create() {
        return new PersonMySQL();
    }
}
