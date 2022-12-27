package com.gdsc.side.api.controller.dto.request.daily;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyRequestDto {
    private String title;
    @NotNull
    private String alertDates;
    private String alertStatus;
    private String alertTime;
    @NotNull
    private String content;

    @Builder
    public DailyRequestDto(String title, String alertDates, String alertStatus, String alertTime, String content) {
        this.title = title;
        this.alertDates = alertDates;
        this.alertStatus = alertStatus;
        this.alertTime = alertTime;
        this.content = content;
    }


}
