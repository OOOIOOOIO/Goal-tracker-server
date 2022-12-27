package com.gdsc.side.api.controller;

import com.gdsc.side.api.config.jwt.JwtUtils;
import com.gdsc.side.api.controller.dto.request.goal.GoalRequestDto;
import com.gdsc.side.api.controller.dto.request.goal.GoalStatusChangeDto;
import com.gdsc.side.api.controller.dto.response.goal.GoalResponseDto;
import com.gdsc.side.api.service.GoalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/goal")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;
    private final JwtUtils jwtUtils;

    /**
     * 장기 목표 조회
     * @param goalId
     * @return
     */
    @GetMapping("/{goalId}")
    public ResponseEntity<GoalResponseDto> getGoal(@PathVariable(name = "goalId") Long goalId) {

        // 조회
        GoalResponseDto goalInfo = goalService.getGoalInfo(goalId);

        return new ResponseEntity<>(goalInfo, HttpStatus.OK);
    }

    /**
     * 장기 목표 저장
     * user 필요
     * @param goalRequestDto
     * @return
     */
    @PostMapping()
    public ResponseEntity<String> saveGoal(HttpServletRequest request, @RequestBody GoalRequestDto goalRequestDto) {

        //헤더에서 jwt-auth-token 조회
        String accessToken = request.getHeader("jwt-auth-token");

        // jwt에서 username 가져오기
        String username = jwtUtils.getUserNameFromJwtToken(accessToken);

        // 저장
        goalService.saveGoal(goalRequestDto, username);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    /**
     * 장기 목표 수정
     *
     * @param goalId
     * @param goalRequestDto
     * @return
     */
    @PutMapping("/{goalId}")
    public ResponseEntity<String> updateGoal(@PathVariable(name = "goalId") Long goalId,
                                             @RequestBody GoalRequestDto goalRequestDto) {

        // 수정
        goalService.updateGoal(goalRequestDto, goalId);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    /**
     * 장기 목표 삭제
     * @param goalId
     * @return
     */
    @DeleteMapping("/{goalId}")
    public ResponseEntity<String> deleteGoal(@PathVariable(name = "goalId") Long goalId) {

        // 삭제
        goalService.deleteGoal(goalId);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    /**
     * 장기 목표 상태 수정
     * @param goalId
     * @return
     */
    @PatchMapping("/{goalId}/status")
    public ResponseEntity<String> goalStatusChange(@PathVariable(name = "goalId") Long goalId,
                                                   @RequestBody GoalStatusChangeDto goalStatusChangeDto) {
     
        // 수정
        goalService.updateGoalStatus(goalId, goalStatusChangeDto);
        
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


}
