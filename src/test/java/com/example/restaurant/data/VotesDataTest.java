package com.example.restaurant.data;

import com.example.restaurant.MatcherFactory;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.User;
import com.example.restaurant.model.Votes;

import java.time.LocalDateTime;

public class VotesDataTest {

    public static final int VOTES_ID = 100029;

    public static final int VOTE_NOT_FOUND = 100;

    public static Restaurant restaurant = new Restaurant(RestaurantDataTest.RESTAUR_ID, "Кофейня");
    public static User user = new User();
    public static Votes votes = new Votes(VOTES_ID, LocalDateTime.of(2024, 10, 18, 10, 00), restaurant, user);

    public static final MatcherFactory.Matcher<Votes> VOTES_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Votes.class, "user", "restaurant");

    public static Votes getUpdate() {
        return new Votes(VOTES_ID, LocalDateTime.of(2020, 10, 10, 10, 00), restaurant, user);
    }

    public static Votes getNew() {
        return new Votes(null, LocalDateTime.of(2022, 10, 10, 10, 00), restaurant, user);
    }
}
