package com.example.restaurant.web;

import com.example.restaurant.model.Vote;
import com.example.restaurant.service.MenuService;
import com.example.restaurant.service.VoteService;
import com.example.restaurant.to.MenuTo;
import com.example.restaurant.util.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    static final String REST_URL = "/rest/vote";

    @Autowired
    private VoteService voteService;

    @Autowired
    private MenuService menuService;

    @GetMapping(path = "/{id}")
    public Vote get(@PathVariable int id) {
        return voteService.get(id);
    }

    @PostMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Vote> save(@PathVariable int id, @AuthenticationPrincipal AuthorizedUser authUser) {
        Vote created = voteService.save(id, authUser.getId());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);

    }

    @GetMapping()
    public List<MenuTo> getAllWithDate() {
        LocalDate localDate = LocalDate.now();
        return menuService.getAllWithDate(localDate);
    }
}
