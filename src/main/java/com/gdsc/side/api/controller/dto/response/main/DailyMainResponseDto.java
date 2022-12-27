package com.gdsc.side.api.controller.dto.response.main;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyMainResponseDto {
    private Long dailyId;
    private String title;
    private String dailyStatus;

    @Builder
    public DailyMainResponseDto(Long dailyId, String title, String dailyStatus) {
        this.dailyId = dailyId;
        this.title = title;
        this.dailyStatus = dailyStatus;
    }
}
