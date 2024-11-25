package com.github.stanyslavizmaylov.restaurant.service;

import com.github.stanyslavizmaylov.restaurant.model.Menu;
import com.github.stanyslavizmaylov.restaurant.repository.CrudMenuRepository;
import com.github.stanyslavizmaylov.restaurant.repository.CrudRestaurantRepository;
import com.github.stanyslavizmaylov.restaurant.util.ValidationUtil;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class MenuService {

    private final CrudMenuRepository crudMenuRepository;

    private final CrudRestaurantRepository crudRestaurantRepository;

    public MenuService(CrudMenuRepository crudMenuRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMenuRepository = crudMenuRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }


    public void delete(int id, int restaurId) {
        ValidationUtil.checkNotFoundWithId(crudMenuRepository.delete(id, restaurId), id);
    }

    @Transactional
    public Menu get(int id, int restaurId) {
        Menu menu1 = crudMenuRepository.findById(id)
                .filter(menu -> menu.getRestaurant().getId() == restaurId)
                .orElse(null);
        ValidationUtil.checkNotFoundWithId(menu1, id);
        Hibernate.initialize(menu1.getMenuItems());
        return menu1;
    }

    @Transactional
    public void update(Menu menu, int restaurId, int id) {
        Assert.notNull(menu, "menu must not be null");
        ValidationUtil.assureIdConsistent(menu, id);
        ValidationUtil.checkNotFoundWithId(get(id, restaurId), id);
        menu.setRestaurant(crudRestaurantRepository.getReferenceById(restaurId));
        crudMenuRepository.save(menu);
    }

    @Transactional
    public Menu save(Menu menu, int restaurId) {
        Assert.notNull(menu, "menu must not be null");
        if (!menu.isNew() && get(menu.getId(), restaurId) != null) {
            return null;
        }
        menu.setRestaurant(crudRestaurantRepository.getReferenceById(restaurId));
        return crudMenuRepository.save(menu);
    }
}
