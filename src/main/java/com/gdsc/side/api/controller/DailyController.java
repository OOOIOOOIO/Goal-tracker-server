package com.gdsc.side.api.controller;

import com.gdsc.side.api.config.jwt.JwtUtils;
import com.gdsc.side.api.controller.dto.request.daily.DailyDatesRequestDto;
import com.gdsc.side.api.controller.dto.request.daily.DailyRequestDto;
import com.gdsc.side.api.controller.dto.request.daily.DailyDatesStatusChangeDto;
import com.gdsc.side.api.controller.dto.response.daily.DailyResponseDto;
import com.gdsc.side.api.service.DailyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/api/daily")
@RequiredArgsConstructor
public class DailyController {

    private final DailyService dailyService;
    private final JwtUtils jwtUtils;

    /**
     * 단기 목표 조회
     * @param dailyId
     * @return
     */
    @GetMapping("/{dailyId}/{date}")
    public ResponseEntity<DailyResponseDto> getDaily(@PathVariable(name = "dailyId") Long dailyId,
                                                     @PathVariable(name = "date") String date){

        // 조회
        DailyResponseDto dailyInfo = dailyService.getDailyInfo(dailyId, LocalDate.parse(date));

        return new ResponseEntity<>(dailyInfo, HttpStatus.OK);
    }

    /**
     * 단기 목표 저장
     * user 필요
     * @param dailyRequestDto
     * @return
     */
    @PostMapping()
    public ResponseEntity<String> saveDaily(HttpServletRequest request, @RequestBody DailyRequestDto dailyRequestDto){

        //헤더에서 jwt-auth-token 조회
        String accessToken = request.getHeader("jwt-auth-token");

        // jwt에서 username 가져오기
        String username = jwtUtils.getUserNameFromJwtToken(accessToken);

        // 저장
        dailyService.saveDaily(dailyRequestDto, username);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    /**
     * 단기 목표 수정
     *
     * @param dailyId
     * @param dailyModifyRequestDto
     * @return
     */
    @PutMapping("/{dailyId}")
    public ResponseEntity<String> updateDaily(@PathVariable("dailyId") Long dailyId,
                                              @RequestBody DailyRequestDto dailyModifyRequestDto) {

        // 수정
        dailyService.updateDaily(dailyModifyRequestDto, dailyId);
        
        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    /**
     * 단기 목표 삭제
     * @param dailyId
     * @return
     */
    @DeleteMapping("/{dailyId}")
    public ResponseEntity<String> deleteDaily(@PathVariable("dailyId") Long dailyId) {

        // 삭제
        dailyService.deleteDaily(dailyId);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    /**
     * 단기 목표 날짜 저장
     * @param dailyId
     * @param date
     * @param dailyDatesRequestDto
     * @return
     */
    @PostMapping("/{dailyId}/{date}")
    public ResponseEntity<String> saveDailyDates(@PathVariable(name = "dailyId") Long dailyId,
                                                 @PathVariable(name = "date") String date,
                                                 @RequestBody DailyDatesRequestDto dailyDatesRequestDto){

        //저장
        dailyService.saveDailyDates(dailyId, LocalDate.parse(date), dailyDatesRequestDto);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    /**
     * 단기 목표 날짜 상태 수정
     * @param dailyId
     * @param date
     * @param dailyDatesStatusChangeDto
     * @return
     */
    @PatchMapping("/{dailyId}/{date}/status")
    public ResponseEntity<String> updateDailyStatus(@PathVariable(name = "dailyId") Long dailyId,
                                               @PathVariable(name = "date") String date,
                                               @RequestBody DailyDatesStatusChangeDto dailyDatesStatusChangeDto){

        // 팝업 창 뜨기 전에 ON으로 할 경우
        // dailyId, date으로 status를 확인 해 없을 경우 save
        // 만약 있을 경우 그대로 update
        dailyService.updateDailyDates(dailyId, LocalDate.parse(date), dailyDatesStatusChangeDto);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }


}
