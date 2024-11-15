package com.example.restaurant.web;

import com.example.restaurant.model.Menu;
import com.example.restaurant.service.MenuService;
import com.example.restaurant.to.MenuTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController {
    static final String REST_URL = "/rest/admin/restaurant";

    @Autowired
    private MenuService menuService;

    @GetMapping(path = REST_URL + "/{restaurId}/menu/{menuId}")
    public Menu get(@PathVariable int restaurId, @PathVariable int menuId) {
        return menuService.get(menuId, restaurId);
    }

    @GetMapping(path = REST_URL + "/{restaurId}/menu/with-date")
    public Menu getWithDate(@PathVariable int restaurId, @RequestParam LocalDate localDate) {
        return menuService.getWithDate(restaurId, localDate);
    }

    @DeleteMapping(REST_URL + "/{restaurId}/menu/{menuId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurId, @PathVariable int menuId) {
        menuService.delete(menuId, restaurId);
    }

    @PutMapping(value = REST_URL + "/{restaurId}/menu/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Validated Menu menu, @PathVariable int restaurId, @PathVariable int menuId) {
        menuService.update(menu, restaurId, menuId);
    }

    @PostMapping(value = REST_URL + "/{restaurId}/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@RequestBody @Validated Menu menu, @PathVariable int restaurId) {
        Menu created = menuService.save(menu, restaurId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurId}/menu/{id}")
                .buildAndExpand(created.getRestaurant().getId(),created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(name = "/rest/menu/to-day")
    public List<MenuTo> getAllToDay() {
        LocalDate localDate = LocalDate.now();
        return menuService.getAllWithDate(localDate);
    }
}
