package com.gdsc.side.api.service;

import com.gdsc.side.api.controller.dto.request.goal.GoalRequestDto;
import com.gdsc.side.api.controller.dto.request.goal.GoalStatusChangeDto;
import com.gdsc.side.api.controller.dto.response.goal.GoalResponseDto;
import com.gdsc.side.api.controller.dto.response.main.GoalMainResponseDto;

import java.util.List;

public interface GoalService {

    /**
     * 장기 목표 조회
     */
    GoalResponseDto getGoalInfo(Long goalId);

    /**
     * 장기 목표 저장
     */
    void saveGoal(GoalRequestDto goalRequestDto, String username);

    /**
     * 장기 목표 수정
     */
    void updateGoal(GoalRequestDto goalRequestDto, Long goalId);

    /**
     * 장기 목표 삭제
     */
    void deleteGoal(Long goalId);

    /**
     * 장기 목표 상태 수정
     */
    void updateGoalStatus(Long goalId, GoalStatusChangeDto goalStatusChangeDto);

    /**
     * 1달치 장기 목표 조회
     */
    List<GoalMainResponseDto> getGoalMonthly(String date);


}
