package com.example.restaurant.web;

import com.example.restaurant.model.User;
import com.example.restaurant.service.UserService;
import com.example.restaurant.util.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {
    static final String REST_URL = "/rest/profile";

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public User get(@AuthenticationPrincipal AuthorizedUser authUser) {
        return userService.get(authUser.getId());
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authUser) {
        userService.delete(authUser.getId());
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Validated User user, @AuthenticationPrincipal AuthorizedUser authUser) {
        userService.update(user,authUser.getId());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> register(@RequestBody @Validated User user) {
        User created = userService.save(user);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
