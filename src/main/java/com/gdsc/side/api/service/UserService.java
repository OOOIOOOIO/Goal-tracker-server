package com.gdsc.side.api.service;

import com.gdsc.side.api.controller.dto.response.calender.CalenderResponseListDto;
import com.gdsc.side.api.controller.dto.response.goal.GoalCompleteListResponseDto;

public interface UserService {

    /**
     *
     */

    /**
     *
     */

    /**
     *
     */

    /**
     * 캘린더, 1달 단위 dailyDates 조회
     */
    CalenderResponseListDto completeDaily(String username);

    /**
     * 완료된 장기 목표 조회
     */
    GoalCompleteListResponseDto completeGoals(String username);

}
