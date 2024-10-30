package com.example.restaurant.repository;

import com.example.restaurant.data.RestaurantDataTest;
import com.example.restaurant.data.UserDataTest;
import com.example.restaurant.data.VotesDataTest;
import com.example.restaurant.model.User;
import com.example.restaurant.model.Votes;
import com.example.restaurant.service.VotesService;
import com.example.restaurant.util.exeption.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static com.example.restaurant.data.UserDataTest.USER_MATCHER;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Sql(scripts = {"/data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class VotesServiceTest {

    @Autowired
    private VotesService votesService;
    @Test
    public void get() {
        Votes votes = votesService.get(VotesDataTest.VOTES_ID);
        VotesDataTest.VOTES_MATCHER.assertMatch(VotesDataTest.votes,votes);
    }

    @Test
    void delete() {
        votesService.delete(VotesDataTest.VOTES_ID);
        assertThrows(NotFoundException.class, () -> votesService.get(RestaurantDataTest.RESTAUR_ID));
    }
    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> votesService.get(VotesDataTest.VOTE_NOT_FOUND));
    }

    @Test
    public void save() {
        Votes created = votesService.save(RestaurantDataTest.RESTAUR_ID,UserDataTest.USER_ID);
        int newId = created.getId();
        Votes newVotes = VotesDataTest.getNew();
        newVotes.setId(newId);
        VotesDataTest.VOTES_MATCHER.assertMatch(created, newVotes);
        VotesDataTest.VOTES_MATCHER.assertMatch(votesService.get(newId), newVotes);

    }
}
