package com.gdsc.side.api.controller.dto.response.goal;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoalCompleteListResponseDto {
    private List<GoalResponseDtoForCompleteListDto> goalResList;

    public GoalCompleteListResponseDto(List<GoalResponseDtoForCompleteListDto> goalResList) {
        this.goalResList = goalResList;
    }
}
