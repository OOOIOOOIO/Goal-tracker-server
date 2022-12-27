package com.gdsc.side.api.controller.dto.response.main;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoalMainResponseDto {
    private Long goalId;
    private String title;
    private String goalStatus;

    @Builder
    public GoalMainResponseDto(Long goalId, String title, String goalStatus) {
        this.goalId = goalId;
        this.title = title;
        this.goalStatus = goalStatus;
    }
}
