package com.example.restaurant.repository;

import com.example.restaurant.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurId")
    int delete(@Param("id") int id, @Param("restaurId") int restaurId);

    @Transactional
    @Query("SELECT m FROM Menu m WHERE m.menuDate=:date")
    List<Menu> getAllWithDate(@Param("date") LocalDate localDate);

    @Transactional
    @Query("SELECT m FROM Menu m WHERE m.menuDate=:date AND m.restaurant.id=:restaurId")
    Menu getWithDate(@Param("date") LocalDate localDate, @Param("restaurId") int restaurId);
}
