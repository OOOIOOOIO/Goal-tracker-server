package com.gdsc.side.api.controller.dto.response.daily;

import com.gdsc.side.api.domain.AlertStatus;
import com.gdsc.side.api.domain.Daily;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyResponseForListDto {
    private Long dailyId;
    private String title;
    private String alertDates;
    private AlertStatus alertStatus;
    private String alertTime;
    private String content;



    public DailyResponseForListDto(Daily entity){
        this.dailyId = entity.getDailyId();
        this.title = entity.getTitle();
        this.alertDates = entity.getAlertDates();
        this.alertStatus = entity.getAlertStatus();
        this.alertTime = entity.getAlertTime();
        this.content = entity.getContent();
    }


}
