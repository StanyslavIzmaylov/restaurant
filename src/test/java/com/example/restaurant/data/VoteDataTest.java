package com.example.restaurant.data;

import com.example.restaurant.MatcherFactory;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.Vote;

import java.time.LocalDate;

import static com.example.restaurant.data.RestaurantDataTest.RESTAUR_ID;

public class VoteDataTest {

    public static final int VOTE_ID = 100029;

    public static final int VOTE_NOT_FOUND = 100;

    public static Restaurant restaurant = new Restaurant(RESTAUR_ID - 1, "Кофейня");
    public static Restaurant restaurant1 = new Restaurant(RESTAUR_ID + 2, "Столовая");
    public static Vote vote = getVote();

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "user","vote_time","restaurant");


    public static Vote getVote() {
        Vote vote = new Vote(VOTE_ID, restaurant);
        vote.setVoteDate(LocalDate.now());
        vote.setUser(UserDataTest.user);
        return vote;
    }

    public static Vote getUpdate() {
        Vote vote = new Vote(VOTE_ID, restaurant1);
        vote.setVoteDate(LocalDate.now());
        vote.setUser(UserDataTest.user);
        return vote;
    }

    public static Vote getUpdateWithToDayDate() {
        Vote vote = new Vote(VOTE_ID, restaurant);
        vote.setVoteDate(LocalDate.now());
        vote.setUser(UserDataTest.user);
        return vote;
    }

    public static Vote getNew() {
        Vote vote = new Vote(null, restaurant);
        vote.setVoteDate(LocalDate.now());
        vote.setUser(UserDataTest.user2);
        return vote;
    }
}
