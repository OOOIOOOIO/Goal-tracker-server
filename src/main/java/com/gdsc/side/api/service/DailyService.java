package com.gdsc.side.api.service;

import com.gdsc.side.api.controller.dto.request.daily.DailyDatesRequestDto;
import com.gdsc.side.api.controller.dto.request.daily.DailyDatesStatusChangeDto;
import com.gdsc.side.api.controller.dto.request.daily.DailyRequestDto;
import com.gdsc.side.api.controller.dto.response.daily.DailyResponseDto;
import com.gdsc.side.api.controller.dto.response.main.DailyCalenderResponseInterface;
import com.gdsc.side.api.domain.Daily;

import java.time.LocalDate;
import java.util.List;

public interface DailyService {

    /**
     * 단기 목표 조회
     */
    DailyResponseDto getDailyInfo(Long dailyId, LocalDate date);

    /**
     * 단기 목표 저장
     */
    void saveDaily(DailyRequestDto dailyRequestDto, String username);

    /**
     * 단기 목표 수정
     */
    void updateDaily(DailyRequestDto dailyRequestDto, Long dailyId);

    /**
     * 단기 목표 삭제
     */
    void deleteDaily(Long dailyId);

    /**
     * 단기 목표 날짜 저장
     */
    void saveDailyDates(Long dailyId, LocalDate date, DailyDatesRequestDto dailyDatesRequestDto);

    /**
     * 단기 목표 날짜 상태 수정
     */
    void checkAndUpdateDailyDates(Long dailyId, LocalDate date, DailyDatesStatusChangeDto dailyDatesStatusChangeDto);

}
