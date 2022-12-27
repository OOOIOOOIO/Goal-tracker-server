package com.gdsc.side.api.controller.dto.request.daily;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.gdsc.side.api.domain.Daily;
import com.gdsc.side.api.domain.DailyDates;
import com.gdsc.side.api.domain.DailyStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyDatesStatusChangeDto {
    private String dailyStatusChange;

    @JsonCreator
    public DailyDatesStatusChangeDto(String dailyStatusChange) {
        this.dailyStatusChange = dailyStatusChange;
    }


}
