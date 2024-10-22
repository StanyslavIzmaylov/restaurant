package com.example.restaurant.data;

import com.example.restaurant.MatcherFactory;
import com.example.restaurant.model.Role;
import com.example.restaurant.model.User;
import com.example.restaurant.model.Votes;

public class UserDataTest {
    public static final int ADMIN_ID = 100023;
    public static final int USER_ID = 100024;
    public static final int USER_NOT_FOUND = 100;
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class,"password");

    public static User user = new User(USER_ID,"User","user","user@ya.ru", Role.USER);
    public static User admin = new User(ADMIN_ID,"Admin","admin","admin@ya.ru", Role.ADMIN);

    public static User getUpdate(){
        return new User(USER_ID,"updateUser","newPass","user@ya.ru", Role.USER);
    }

    public static User getNew(){

        return new User(null,"newUser","user","newuser@ya.ru", Role.USER);
    }
}
