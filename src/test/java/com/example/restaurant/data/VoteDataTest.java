package com.example.restaurant.data;

import com.example.restaurant.MatcherFactory;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.User;
import com.example.restaurant.model.Vote;

import java.time.LocalDate;

import static com.example.restaurant.data.RestaurantDataTest.RESTAUR_ID;

public class VoteDataTest {

    public static final int VOTE_ID = 100029;

    public static final int VOTE_NOT_FOUND = 100;

    public static Restaurant restaurant = new Restaurant(RESTAUR_ID - 1, "Кофейня");

    public static Restaurant restaurant1 = new Restaurant(RESTAUR_ID, "Рыбный ресторан");
    public static User user = new User();
    public static Vote vote = new Vote(VOTE_ID ,LocalDate.now().plusDays(1), restaurant, user);

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "user", "restaurant","voteDateTime");

    public static Vote getUpdate() {
        return new Vote(VOTE_ID,LocalDate.now(), restaurant1, user);
    }
    public static Vote getUpdateWithToDayDate() {
        return new Vote(VOTE_ID + 1 ,LocalDate.now(), restaurant, user);
    }
    public static Vote getNew() {
        return new Vote(null,LocalDate.now(), restaurant, user);
    }
}
