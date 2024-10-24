package com.example.restaurant.service;

import com.example.restaurant.model.Menu;
import com.example.restaurant.repository.menu.DataJpaMenuRepository;
import com.example.restaurant.to.MenuTo;
import com.example.restaurant.util.MenuUtil;
import com.example.restaurant.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

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

    public Menu save(Menu menu, int restaurId){
        Assert.notNull(menu, "menu must not be null");
       return dataJpaMenuRepository.save(menu,restaurId);
    }
    public List<Menu> getAll(){
        return  dataJpaMenuRepository.getAll();
    }
    @Cacheable("menu")
    public List<MenuTo> getAllWithDate(LocalDate localDate){
      return  MenuUtil.getTos(dataJpaMenuRepository.getAllWithDate(localDate));
    }
}
