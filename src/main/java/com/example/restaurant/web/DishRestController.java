package com.example.restaurant.web;

import com.example.restaurant.model.MenuItem;
import com.example.restaurant.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {

    static final String REST_URL = "/rest/admin/menu";

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping(path = "/{menuId}/meal/{mealId}")
    public MenuItem get(@PathVariable int menuId, @PathVariable int mealId) {
        return menuItemService.get(mealId, menuId);
    }

    @DeleteMapping("/{menuId}/meal/{mealId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int menuId, @PathVariable int mealId) {
        menuItemService.delete(mealId, menuId);
    }

    @PutMapping(value = "/{menuId}/meal", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Validated MenuItem menuItem, @PathVariable int menuId) {
        menuItemService.save(menuItem, menuId);
    }

    @PostMapping(value = "/{menuId}/meal", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuItem> createWithLocation(@RequestBody @Validated MenuItem menuItem, @PathVariable int menuId) {
        MenuItem created = menuItemService.save(menuItem, menuId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/meals")
    public List<MenuItem> getAll() {
        return menuItemService.getAll();
    }
}
