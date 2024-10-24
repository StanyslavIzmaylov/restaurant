package com.example.restaurant.web;

import com.example.restaurant.service.MenuService;
import com.example.restaurant.service.VotesService;
import com.example.restaurant.to.MenuTo;
import com.example.restaurant.to.VotesTo;
import com.example.restaurant.util.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VotesRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VotesRestController {
    static final String REST_URL = "/rest/votes";

    @Autowired
    private VotesService votesService;

    @Autowired
    private MenuService menuService;

    @GetMapping(path = "/{id}")
    public VotesTo get(@PathVariable int id) {
        return votesService.getTo(id);
    }

    @PostMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addVote(@PathVariable int id, @AuthenticationPrincipal AuthorizedUser authUser) {
        votesService.save(id, authUser.getId());
    }

    @GetMapping()
    public List<MenuTo> getAllWithDate() {
        LocalDate localDate = LocalDate.now();
        return menuService.getAllWithDate(localDate);
    }
}
