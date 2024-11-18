package com.example.restaurant.web;

import com.example.restaurant.model.User;
import com.example.restaurant.service.UserService;
import com.example.restaurant.util.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {
    static final String REST_URL = "/rest/profile";

    @Autowired
    private UserService userService;


    @GetMapping(path = REST_URL)
    public User get(@AuthenticationPrincipal AuthorizedUser authUser) {
        return userService.get(authUser.getId());
    }

    @DeleteMapping(path = REST_URL)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authUser) {
        userService.delete(authUser.getId());
    }

    @PutMapping(path = REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Validated User user, @AuthenticationPrincipal AuthorizedUser authUser) {
        userService.update(user, authUser.getId());
    }

    @PostMapping(path = REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody @Validated User user) {
        User created = userService.save(user);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(path = "/rest/admin/all-user")
    public List<User> getAll(@AuthenticationPrincipal AuthorizedUser authUser) {
        return userService.getAll();
    }
}
