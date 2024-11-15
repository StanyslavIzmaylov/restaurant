package com.example.restaurant.service;

import com.example.restaurant.model.Menu;
import com.example.restaurant.repository.CrudMenuRepository;
import com.example.restaurant.repository.CrudRestaurantRepository;
import com.example.restaurant.to.MenuTo;
import com.example.restaurant.util.MenuUtil;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.example.restaurant.util.ValidationUtil.assureIdConsistent;
import static com.example.restaurant.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {

    private final CrudMenuRepository crudMenuRepository;

    private final CrudRestaurantRepository crudRestaurantRepository;

    public MenuService(CrudMenuRepository crudMenuRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMenuRepository = crudMenuRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }


    public void delete(int id, int restaurId) {
        checkNotFoundWithId(crudMenuRepository.delete(id, restaurId), id);
    }

    @Transactional
    public Menu get(int id, int restaurId) {
      Menu menu1 =  crudMenuRepository.findById(id)
                .filter(menu -> menu.getRestaurant().getId() == restaurId)
                .orElse(null);
        checkNotFoundWithId(menu1, id);
        Hibernate.initialize(menu1.getMenuItems());
        return menu1;
    }

    public Menu getWithDate(int restaurId, LocalDate localDate) {
        return crudMenuRepository.getWithDate(localDate, restaurId);
    }

    @Transactional
    public void update(Menu menu, int restaurId, int id) {
        Assert.notNull(menu, "menu must not be null");
        assureIdConsistent(menu, id);
        checkNotFoundWithId(get(id, restaurId), id);
        menu.setRestaurant(crudRestaurantRepository.getReferenceById(restaurId));
        crudMenuRepository.save(menu);
    }

    @Transactional
    public Menu save(Menu menu, int restaurId) {
        Assert.notNull(menu, "menu must not be null");
        if (!menu.isNew() && get(menu.getId(), restaurId) == null) {
            return null;
        }
        menu.setRestaurant(crudRestaurantRepository.getReferenceById(restaurId));
        return crudMenuRepository.save(menu);
    }

    public List<MenuTo> getAllWithDate(LocalDate localDate) {
        return MenuUtil.getTos(crudMenuRepository.getAllWithDate(localDate));
    }
}
