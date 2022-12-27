package com.gdsc.side.api.service;

import com.gdsc.side.api.controller.dto.request.diary.DiaryRequestDto;
import com.gdsc.side.api.controller.dto.response.diary.DiaryResponseDto;
import com.gdsc.side.api.domain.Diary;
import com.gdsc.side.api.domain.Goal;
import com.gdsc.side.api.exception.type.DiaryNotFoundException;
import com.gdsc.side.api.exception.type.GoalNotFoundException;
import com.gdsc.side.api.repository.DiaryRepository;
import com.gdsc.side.api.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DiaryServiceImpl implements DiaryService{

    private final DiaryRepository diaryRepository;
    private final GoalRepository goalRepository;

    @Override
    public DiaryResponseDto getDiaryInfo(Long diaryId) {
        // diary 조회
        Diary diary = diaryRepository.findByDiaryId(diaryId).orElseThrow(() -> new DiaryNotFoundException("diary is not exist"));

        return new DiaryResponseDto(diary);
    }

    @Override
    public void saveDiary(DiaryRequestDto diaryRequestDto, Long goalId) {
        // goal 조회
        Goal goal = goalRepository.findByGoalId(goalId).orElseThrow(() -> new GoalNotFoundException("goal is not exist"));

        // diary 생성
        Diary diary = Diary.createDiary(diaryRequestDto, goal);

        //저장
        diaryRepository.save(diary);

    }

    @Override
    public void updateDiary(DiaryRequestDto diaryRequestDto, Long diaryId) {
        // diary 조회
        Diary diary = diaryRepository.findByDiaryId(diaryId).orElseThrow(() -> new DiaryNotFoundException("diary is not exist"));

        // dirty checking
        diary.updateDiary(diaryRequestDto);

    }

    @Override
    public void deleteDiary(Long diaryId) {
        diaryRepository.deleteById(diaryId);

    }
}
