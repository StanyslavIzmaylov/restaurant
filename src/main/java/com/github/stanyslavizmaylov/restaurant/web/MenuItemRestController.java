package com.github.stanyslavizmaylov.restaurant.web;

import com.github.stanyslavizmaylov.restaurant.model.MenuItem;
import com.github.stanyslavizmaylov.restaurant.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = MenuItemRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuItemRestController {

    static final String REST_URL = "/rest/admin/menu";

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping(path = "/{menuId}/menu-item/{menuItemId}")
    public MenuItem get(@PathVariable int menuId, @PathVariable int menuItemId) {
        return menuItemService.get(menuItemId, menuId);
    }

    @DeleteMapping("/{menuId}/menu-item/{menuItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int menuId, @PathVariable int menuItemId) {
        menuItemService.delete(menuItemId, menuId);
    }

    @PutMapping(value = "/{menuId}/menu-item/{menuItemId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Validated MenuItem menuItem, @PathVariable int menuId, @PathVariable int menuItemId) {
        menuItemService.update(menuItem, menuItemId, menuId);
    }

    @PostMapping(value = "/{menuId}/menu-item", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuItem> createWithLocation(@RequestBody @Validated MenuItem menuItem, @PathVariable int menuId) {
        MenuItem created = menuItemService.save(menuItem, menuId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{menuId}/menu-item/{id}")
                .buildAndExpand(created.getMenu().getId(), created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
