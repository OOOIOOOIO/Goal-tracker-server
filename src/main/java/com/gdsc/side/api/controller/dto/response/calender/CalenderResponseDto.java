package com.gdsc.side.api.controller.dto.response.calender;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.gdsc.side.api.domain.DailyDates;
import com.gdsc.side.api.domain.DailyStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CalenderResponseDto {

    private HashMap<String, List<?>> calenderResponseList;

    @Builder
    @JsonCreator
    public CalenderResponseDto(HashMap<String, List<?>> calenderResponseList) {
        this.calenderResponseList = calenderResponseList;
    }
}
