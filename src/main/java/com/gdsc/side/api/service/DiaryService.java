package com.gdsc.side.api.service;

import com.gdsc.side.api.controller.dto.request.diary.DiaryRequestDto;
import com.gdsc.side.api.controller.dto.response.diary.DiaryResponseDto;

public interface DiaryService {

    /**
     * 일기 조회
     */
    DiaryResponseDto getDiaryInfo(Long goal_id);

    /**
     * 일기 저장
     */
    void saveDiary(DiaryRequestDto diaryRequestDto, Long goalId);

    /**
     * 일기 수정
     */
    void updateDiary(DiaryRequestDto diaryRequestDto, Long diaryId);

    /**
     * 일기 삭제
     */
    void deleteDiary(Long diaryId);
}
