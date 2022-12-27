package com.gdsc.side.api.controller.dto.response.goal;

import com.gdsc.side.api.domain.AlertStatus;
import com.gdsc.side.api.domain.Goal;
import com.gdsc.side.api.domain.GoalStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoalResponseDto {
    private Long goalId;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private AlertStatus alertStatus;
    private String alertTime;
    private String content;
    private GoalStatus goalStatus;

    public GoalResponseDto(Goal entity){
        this.goalId = entity.getGoalId();
        this.title = entity.getTitle();
        this.startDate = entity.getCreatedAt().toLocalDate();
        this.endDate = entity.getEndDate();
        this.alertStatus = entity.getAlertStatus();
        this.alertTime = entity.getAlertTime();
        this.content = entity.getContent();
        this.goalStatus = entity.getGoalStatus();
    }
}
