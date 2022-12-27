package com.gdsc.side.api.controller.dto.response.goal;

import com.gdsc.side.api.domain.Goal;
import com.gdsc.side.api.domain.GoalStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoalResponseDtoForCompleteListDto {
    private Long goalId;
    private String title;
    private GoalStatus goalStatus;

    public GoalResponseDtoForCompleteListDto(Goal entity){
        this.goalId = entity.getGoalId();
        this.title = entity.getTitle();
        this.goalStatus = entity.getGoalStatus();
    }
}
