package com.gdsc.side.api.controller;

import com.gdsc.side.api.controller.dto.response.main.MainResponseDto;
import com.gdsc.side.api.service.DailyService;
import com.gdsc.side.api.service.GoalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainController {

    private final DailyService dailyService;
    private final GoalService goalService;

    /**
     * 1달 단위 daily, dailyDates, goal 조회
     * month가 바뀔 경우, 1달 단위 daily, dailyDates, goal 조회
     * @return
     */
    @GetMapping(value = {"/{month}", "/movement/{month}"})
    public ResponseEntity<MainResponseDto> getData(@PathVariable(name = "month") String month){

        // 조회
        
        return null;
    }

    /**
     * 옆으로 넘기다 월이 바뀌면 뿌려주기
     * @param month
     * @return
     */
//    @GetMapping("/movement/{month}")
//    public ResponseEntity<MainResponseDto> changeMonth(@PathVariable(name = "month") String month){
//
//        // 조회
//
//        return null;
//    }

}
