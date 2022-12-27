package com.gdsc.side.api.controller.dto.response.calender;

import com.gdsc.side.api.domain.DailyDates;
import com.gdsc.side.api.domain.DailyStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CalenderResponseDto {

    private LocalDate date;
    private DailyStatus dailyStatus;
    private Long dailyId;

    public CalenderResponseDto(DailyDates entity) {
        this.date = entity.getDate();
        this.dailyStatus = entity.getDailyStatus();
        this.dailyId = entity.getDaily().getDailyId();
    }
}
