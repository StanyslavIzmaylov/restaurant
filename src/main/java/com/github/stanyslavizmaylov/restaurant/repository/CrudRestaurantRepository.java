package com.github.stanyslavizmaylov.restaurant.repository;

import com.github.stanyslavizmaylov.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);


    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.menus m LEFT JOIN FETCH m.menuItems WHERE m.menuDate=:date")
    List<Restaurant> getWithMenuAndWithDate(@Param("date") LocalDate localDate);

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.menus m LEFT JOIN FETCH m.menuItems WHERE r.id=:id AND m.menuDate=:date")
    Restaurant getWithDate(@Param("id") int id, @Param("date") LocalDate localDate);

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.menus m LEFT JOIN FETCH m.menuItems WHERE r.id=:id")
    List<Restaurant> getWithAllMenu(@Param("id") int id);
}
