package com.example.restaurant.repository;

import com.example.restaurant.data.RestaurantDataTest;
import com.example.restaurant.data.UserDataTest;
import com.example.restaurant.data.VoteDataTest;
import com.example.restaurant.model.Vote;
import com.example.restaurant.service.VoteService;
import com.example.restaurant.util.exeption.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Sql(scripts = {"/data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class VoteServiceTest {

    @Autowired
    private VoteService voteService;
    @Test
    public void get() {
        Vote vote = voteService.get(VoteDataTest.VOTE_ID);
        VoteDataTest.VOTE_MATCHER.assertMatch(VoteDataTest.vote, vote);
    }

    @Test
    void delete() {
        voteService.delete(VoteDataTest.VOTE_ID);
        assertThrows(NotFoundException.class, () -> voteService.get(VoteDataTest.VOTE_ID));
    }
    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> voteService.get(VoteDataTest.VOTE_NOT_FOUND));
    }

    @Test
    public void save() {
        Vote created = voteService.save(RestaurantDataTest.RESTAUR_ID,UserDataTest.USER_ID);
        int newId = created.getId();
        Vote newVote = VoteDataTest.getNew();
        newVote.setId(newId);
        VoteDataTest.VOTE_MATCHER.assertMatch(created, newVote);
        VoteDataTest.VOTE_MATCHER.assertMatch(voteService.get(newId), newVote);

    }
}
