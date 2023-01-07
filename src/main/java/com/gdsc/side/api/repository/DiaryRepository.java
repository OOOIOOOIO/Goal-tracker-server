package com.gdsc.side.api.repository;

import com.gdsc.side.api.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    /**
     * GET, diary 단건 조회
     */
    Optional<Diary> findByGoal_GoalId(Long goalId);

    /**
     * PUT, diary 수정
     */
    Optional<Diary> findByDiaryId(Long diaryId);

}
