package com.example.restaurant;

import com.example.restaurant.model.Menu;

import java.time.LocalDate;

public class MenuDataTest {
    public static final int MENU_ID = 100007;

    public static final int MENU_NOT_FOUND = 100;
    public static final Menu menu = new Menu(100007, LocalDate.of(2024,10,11));

    public static Menu getUpdate(){
        return new Menu(100007, LocalDate.of(2000,10,07));
    }
}
