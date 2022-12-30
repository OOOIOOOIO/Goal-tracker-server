package com.gdsc.side.api.controller;

import com.gdsc.side.api.config.jwt.JwtUtils;
import com.gdsc.side.api.controller.dto.response.main.MainResponseDto;
import com.gdsc.side.api.service.DailyService;
import com.gdsc.side.api.service.GoalService;
import com.gdsc.side.api.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;
    private final JwtUtils jwtUtils;

    /**
     * 1달 단위 daily, dailyDates, goal 조회
     * month가 바뀔 경우, 1달 단위 daily, dailyDates, goal 조회
     * @return
     */
    @GetMapping(value = {"/{month}", "/movement/{month}"})
    public ResponseEntity<MainResponseDto> getData(HttpServletRequest request,@PathVariable(name = "month") String month){
        //헤더에서 jwt-auth-token 조회
        String accessToken = request.getHeader("jwt-auth-token");

        // jwt에서 username 가져오기
        String username = jwtUtils.getUserNameFromJwtToken(accessToken);


        MainResponseDto dailyAndGoalByMonthly = mainService.getDailyAndGoalByMonthly(month, username);

        return new ResponseEntity<>(dailyAndGoalByMonthly, HttpStatus.OK);
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
