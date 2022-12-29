package com.gdsc.side.api.controller;

import com.gdsc.side.api.config.jwt.JwtUtils;
import com.gdsc.side.api.controller.dto.response.calender.CalenderResponseDto;
import com.gdsc.side.api.controller.dto.response.goal.GoalCompleteListResponseDto;
import com.gdsc.side.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtils jwtUtils;


    /**
     * 캘린더, 1달 단위 dailyDates 조회
     * @return
     */
    @GetMapping("/calender/{month}")
    public ResponseEntity<CalenderResponseDto> dailyAchievements(HttpServletRequest request, @PathVariable(name = "month") String month) {
        //헤더에서 jwt-auth-token 조회
        String accessToken = request.getHeader("jwt-auth-token");

        // jwt에서 username 가져오기
        String username = jwtUtils.getUserNameFromJwtToken(accessToken);

        // 조회
        CalenderResponseDto calenderResponseDto = userService.completeDaily(username, month);

        return new ResponseEntity<>(calenderResponseDto, HttpStatus.OK);
    }

    /**
     * 완료된 장기 목표 조회
     * @return
     */
    @GetMapping("/goals")
    public ResponseEntity<GoalCompleteListResponseDto> getGoals(HttpServletRequest request) {
        //헤더에서 jwt-auth-token 조회
        String accessToken = request.getHeader("jwt-auth-token");

        // jwt에서 username 가져오기
        String username = jwtUtils.getUserNameFromJwtToken(accessToken);

        GoalCompleteListResponseDto completeGoals = userService.completeGoals(username);

        return new ResponseEntity<>(completeGoals, HttpStatus.OK);
    }


}
