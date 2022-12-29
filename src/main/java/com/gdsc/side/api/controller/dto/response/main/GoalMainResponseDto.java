package com.gdsc.side.api.controller.dto.response.main;

import com.gdsc.side.api.domain.Goal;
import com.gdsc.side.api.domain.GoalStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoalMainResponseDto {
    private Long goalId;
    private String title;
    private GoalStatus goalStatus;
    private LocalDate startDate;
    private LocalDate endDate;


    @Builder
    public GoalMainResponseDto(Goal entity) {
        this.goalId = entity.getGoalId();
        this.title = entity.getTitle();
        this.startDate = entity.getCreatedAt().toLocalDate();
        this.endDate = entity.getEndDate();
        this.goalStatus = entity.getGoalStatus();
    }
}
