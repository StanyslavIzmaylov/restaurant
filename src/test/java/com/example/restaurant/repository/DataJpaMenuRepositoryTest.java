package com.example.restaurant.repository;

import com.example.restaurant.model.Menu;
import com.example.restaurant.repository.menu.DataJpaMenuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DataJpaMenuRepositoryTest {

    @Autowired
    DataJpaMenuRepository dataJpaMenuRepository;

    @Test
    public void deleteAll(){
        List<Menu> menuList = dataJpaMenuRepository.getAll();
        menuList.size();
        dataJpaMenuRepository.deleteAllEveryDay();
        List<Menu> menuList1 = dataJpaMenuRepository.getAll();
       menuList1.size();
    }
}
