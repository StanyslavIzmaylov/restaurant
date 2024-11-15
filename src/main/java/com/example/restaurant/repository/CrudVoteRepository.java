package com.example.restaurant.repository;

import com.example.restaurant.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    @Transactional
    @Query("SELECT v FROM Vote v WHERE v.id=:id AND v.voteDate=:voteDate AND v.user.id =:userId")
    Vote getWithDateToDay(@Param("id") int id, @Param("voteDate") LocalDate voteDate, @Param("userId") int userId);
}
