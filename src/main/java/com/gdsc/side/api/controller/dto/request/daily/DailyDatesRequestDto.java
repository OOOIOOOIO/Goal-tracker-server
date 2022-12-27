package com.gdsc.side.api.controller.dto.request.daily;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyDatesRequestDto {

    private String dailyStatus;

    @JsonCreator
    public DailyDatesRequestDto(String dailyStatus) {
        this.dailyStatus = dailyStatus;
    }
}
