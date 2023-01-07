package com.gdsc.side.api.controller.dto.response.main;

import com.gdsc.side.api.domain.AlertStatus;
import com.gdsc.side.api.domain.GoalStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface GoalMainResponseInterface {
    Long getGoal_id();
    String getTitle();
    GoalStatus getGoal_status();
    LocalDateTime getCreated_at();
    LocalDate getEnd_date();
    String getAlert_time();
    AlertStatus getAlert_status();

}
