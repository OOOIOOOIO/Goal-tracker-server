package com.gdsc.side.api.controller;

import com.gdsc.side.api.controller.dto.request.diary.DiaryRequestDto;
import com.gdsc.side.api.controller.dto.response.diary.DiaryResponseDto;
import com.gdsc.side.api.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    /**
     * 일기 조회
     * @param diaryId
     * @return
     */
    @GetMapping("/{diaryId}")
    public ResponseEntity<DiaryResponseDto> getDiary(@PathVariable(name = "diaryId") Long diaryId) {

        // 조회
        DiaryResponseDto diaryInfo = diaryService.getDiaryInfo(diaryId);

        return new ResponseEntity<>(diaryInfo, HttpStatus.OK);
    }

    /**
     * 일기 저장
     * @param diaryRequestDto
     * @return
     */
    @PostMapping("/{goalId}")
    public ResponseEntity<String> saveDiary(@PathVariable(name = "goalId") Long goalId,
                                            @RequestBody DiaryRequestDto diaryRequestDto) {
        // 저장
        diaryService.saveDiary(diaryRequestDto, goalId);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    /**
     * 일기 수정
     * @param diaryId
     * @param diaryRequestDto
     * @return
     */
    @PutMapping("/{diaryId}")
    public ResponseEntity<String> updateDiary(@PathVariable(name = "diaryId") Long diaryId,
                                              @RequestBody DiaryRequestDto diaryRequestDto) {
        // 수정
        diaryService.updateDiary(diaryRequestDto, diaryId);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    /**
     * 일기 삭제
     * @param diaryId
     * @return
     */
    @DeleteMapping("/{diaryId}")
    public ResponseEntity<String> deleteDiary(@PathVariable(name = "diaryId") Long diaryId){

        // 삭제
        diaryService.deleteDiary(diaryId);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
