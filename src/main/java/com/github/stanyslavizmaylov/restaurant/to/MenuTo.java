package com.github.stanyslavizmaylov.restaurant.to;

import java.io.Serializable;
import java.time.LocalDate;

public class MenuTo implements Serializable {
    private Integer id;
    private LocalDate menuDate;

    public MenuTo(Integer id, LocalDate menuDate) {
        this.id = id;
        this.menuDate = menuDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getMenuDate() {
        return menuDate;
    }

    public void setMenuDate(LocalDate menuDate) {
        this.menuDate = menuDate;
    }
}
