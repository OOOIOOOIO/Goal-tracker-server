package com.gdsc.side.api.service;

import com.gdsc.side.api.controller.dto.response.calender.CalenderResponseDto;
import com.gdsc.side.api.controller.dto.response.goal.GoalCompleteListResponseDto;

public interface UserService {


    /**
     * 캘린더, 1달 단위 성공한 daily 조회
     */
    CalenderResponseDto completeDaily(String username, String month);

    /**
     * 성공한 장기 목표 조회
     */
    GoalCompleteListResponseDto completeGoals(String username);

}
