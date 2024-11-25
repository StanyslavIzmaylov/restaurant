package com.github.stanyslavizmaylov.restaurant.data;

import com.github.stanyslavizmaylov.restaurant.MatcherFactory;
import com.github.stanyslavizmaylov.restaurant.model.Role;
import com.github.stanyslavizmaylov.restaurant.model.User;
import com.github.stanyslavizmaylov.restaurant.util.json.JsonUtil;

public class UserDataTest {
    public static final int ADMIN_ID = 100023;
    public static final int USER_ID = 100024;
    public static final int USER_NOT_FOUND = 100;
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class,"password","role");

    public static User admin = new User(ADMIN_ID,"Admin","admin","admin@ya.ru", Role.ADMIN);
    public static User user = new User(USER_ID,"User","user","user@ya.ru", Role.USER);
    public static User user1 = new User(USER_ID + 1,"User1","user","user1@ya.ru", Role.USER);
    public static User user2 = new User(USER_ID + 2,"User2","user","user2@ya.ru", Role.USER);
    public static User user3 = new User(USER_ID + 3,"User3","user","user3@ya.ru", Role.USER);
    public static User user4 = new User(USER_ID,"User4","user","user4@ya.ru", Role.USER);

    public static User getUpdate(){
        return new User(USER_ID,"updateUser","newPass","user@ya.ru", Role.USER);
    }

    public static User getNew(){

        return new User(null,"newUser","user","newuser@ya.ru", Role.USER);
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
