package com.example.restaurant.service;

import com.example.restaurant.model.Menu;
import com.example.restaurant.repository.menu.DataJpaMenuRepository;
import com.example.restaurant.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class MenuService {

    @Autowired
    private final DataJpaMenuRepository dataJpaMenuRepository;

    public MenuService(DataJpaMenuRepository dataJpaMenuRepository) {
        this.dataJpaMenuRepository = dataJpaMenuRepository;
    }

    public void delete(int id, int restaurId){
        ValidationUtil.checkNotFoundWithId(dataJpaMenuRepository.delete(id,restaurId),id);
    }

    public Menu get(int id, int restaurId){
       return ValidationUtil.checkNotFoundWithId(dataJpaMenuRepository.get(id,restaurId),id);
    }

    public void update(Menu menu, int restaurId){
        Assert.notNull(menu, "menu must not be null");
        dataJpaMenuRepository.save(menu,restaurId);
    }

    public void save(Menu menu, int restaurId){
        Assert.notNull(menu, "menu must not be null");
        dataJpaMenuRepository.save(menu,restaurId);
    }

}
