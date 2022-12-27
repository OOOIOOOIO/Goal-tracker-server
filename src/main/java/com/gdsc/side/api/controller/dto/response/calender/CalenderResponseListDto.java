package com.gdsc.side.api.controller.dto.response.calender;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CalenderResponseListDto {

    private List<CalenderResponseDto> calenderResponseDto;

    @JsonCreator
    public CalenderResponseListDto(List<CalenderResponseDto> calenderResponseDto) {
        this.calenderResponseDto = calenderResponseDto;
    }
}
