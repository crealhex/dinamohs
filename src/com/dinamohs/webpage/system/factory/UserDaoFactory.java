package com.dinamohs.webpage.system.factory;

import com.dinamohs.webpage.system.dao.UserDAO;
import com.dinamohs.webpage.system.jdbc.mysql.UserMySQL;

public class UserDaoFactory {

    public static UserDAO create() {
        return new UserMySQL();
    }
}
