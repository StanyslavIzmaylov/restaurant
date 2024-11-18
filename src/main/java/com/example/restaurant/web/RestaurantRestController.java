package com.example.restaurant.web;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.service.RestaurantService;
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
public class RestaurantRestController {
    static final String REST_URL = "/rest/admin/restaurant";

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(path = REST_URL + "/{id}")
    public Restaurant get(@PathVariable int id) {
        return restaurantService.get(id);
    }

    @GetMapping(path = REST_URL + "/{id}/with-all-menu")
    public Restaurant getWithAllMenu(@PathVariable int id) {
        return restaurantService.getWithAllMenu(id);
    }

    @GetMapping(path = REST_URL + "/{id}/with-date")
    public Restaurant getWithDate(@PathVariable int id, @RequestParam LocalDate localDate) {
        return restaurantService.getWithDate(id, localDate);
    }

    @DeleteMapping(REST_URL + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        restaurantService.delete(id);
    }

    @PutMapping(path = REST_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Validated Restaurant restaurant, @PathVariable int id) {
        restaurantService.update(restaurant, id);
    }

    @PostMapping(path = REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody @Validated Restaurant restaurant) {
        Restaurant created = restaurantService.save(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(path = REST_URL + "/all-with-menu-to-day")
    public List<Restaurant> getWithMenuToDay() {
        LocalDate localDate = LocalDate.now();
        return restaurantService.getWithMenuAndWithDate(localDate);
    }

    @GetMapping(path = REST_URL + "/all-with-date")
    public List<Restaurant> getWithMenuAndWithDate(@RequestParam LocalDate localDate) {
        return restaurantService.getWithMenuAndWithDate(localDate);
    }

    @GetMapping(path = REST_URL)
    public List<Restaurant> getAll() {
        return restaurantService.getAll();
    }
}
