package com.github.stanyslavizmaylov.restaurant.repository;

import com.github.stanyslavizmaylov.restaurant.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    @Transactional
    @Query("SELECT v FROM Vote v WHERE v.user.id =:userId")
    List<Vote> getVoteByUser(@Param("userId") int userId);

    @Transactional
    @Query("SELECT v FROM Vote v WHERE v.user.id =:userId AND v.voteDate=:voteDate")
    Vote getVoteByUserAndDateToDay(@Param("userId") int userId, @Param("voteDate") LocalDate voteDate);

    @Transactional
    @Query("SELECT v FROM Vote v WHERE v.voteDate=:voteDate")
    List<Vote> geWithDate(@Param("voteDate") LocalDate voteDate);
}
