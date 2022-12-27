package com.gdsc.side.api.controller.dto.request.goal;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoalRequestDto {
    private String title;
    private LocalDate endDate;
    private String alertStatus;
    private String alertTime;
    private String content;
    private String goalStatus;

    @Builder
    public GoalRequestDto(String title, LocalDate endDate, String alertStatus, String alertTime, String content, String goalStatus) {
        this.title = title;
        this.endDate = endDate;
        this.alertStatus = alertStatus;
        this.alertTime = alertTime;
        this.content = content;
        this.goalStatus = goalStatus;
    }


}
