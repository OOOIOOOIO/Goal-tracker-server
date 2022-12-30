package com.gdsc.side.api.repository;

import com.gdsc.side.api.controller.dto.response.main.GoalMainResponseInterface;
import com.gdsc.side.api.domain.Goal;
import com.gdsc.side.api.domain.GoalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, Long> {

    /**
     * goal 단건 조회
     */
    Optional<Goal> findByGoalId(Long goalId);

    /**
     * complete goals 조회
     * fk로 조회
     */
    List<Goal> findByUser_UserIdAndGoalStatus(Long userId, GoalStatus goalStatus);

    @Query(value = "select g.goal_id, g.title, g.goal_status, g.created_at, g.end_date from goal g where g.created_at like :month1 or g.end_date like :month2 and g.user_id = :user_id", nativeQuery = true)
    List<GoalMainResponseInterface> findGoalByMonth(@Param("month1") String month1, @Param("month2") String month2, @Param("user_id") Long user_id);

}
