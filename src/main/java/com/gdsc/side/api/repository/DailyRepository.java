package com.gdsc.side.api.repository;

import com.gdsc.side.api.controller.dto.response.main.DailyMainResponseDto;
import com.gdsc.side.api.domain.Daily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyRepository extends JpaRepository<Daily, Long> {

    /**
     * daily 단건 조회
     */
    Optional<Daily> findByDailyId(Long dailyId);

    /***
     * 1달치 데이터 가져오기
     * MainController
     */
//    List<DailyMainResponseDto> findDailyByDate(String date);
}
