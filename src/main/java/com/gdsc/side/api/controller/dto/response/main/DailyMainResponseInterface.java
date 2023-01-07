package com.gdsc.side.api.controller.dto.response.main;

import com.gdsc.side.api.domain.AlertStatus;

/**
 * db 컬럼명이랑 똑같이
 */
public interface DailyMainResponseInterface {
    Long getDaily_id();
    String getTitle();
    String getAlert_dates();
    String getDaily_status();
    String getAlert_time();
    AlertStatus getAlert_status();
}
