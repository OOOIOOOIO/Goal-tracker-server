package com.gdsc.side.api.repository;

import com.gdsc.side.api.domain.DailyDates;
import com.gdsc.side.api.domain.Goal;
import com.gdsc.side.api.domain.GoalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
