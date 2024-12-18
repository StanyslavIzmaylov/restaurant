package com.github.stanyslavizmaylov.restaurant.data;

import com.github.stanyslavizmaylov.restaurant.MatcherFactory;
import com.github.stanyslavizmaylov.restaurant.model.Menu;

import java.time.LocalDate;

public class MenuDataTest {

    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Menu.class, "menuItems","restaurant");
    public static final int MENU_ID = 100007;
    public static final int MENU_NOT_FOUND = 100;
    public static final Menu menu = new Menu(100007, LocalDate.now().plusDays(1));

    public static Menu getUpdate(){
        return new Menu(100007, LocalDate.of(2000,10,07));
    }

    public static Menu getNew(){
        return new Menu(null,LocalDate.of(2020,01,01));
    }
}
