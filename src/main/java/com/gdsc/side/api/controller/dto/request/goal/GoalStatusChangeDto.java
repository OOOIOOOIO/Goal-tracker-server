package com.gdsc.side.api.controller.dto.request.goal;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoalStatusChangeDto {
    private String goalStatusChange;

    @JsonCreator
    public GoalStatusChangeDto(String goalStatusChange) {
        this.goalStatusChange = goalStatusChange;
    }
}
