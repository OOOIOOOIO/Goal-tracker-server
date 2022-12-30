package com.gdsc.side.api.repository;

import com.gdsc.side.api.controller.dto.response.main.DailyMainResponseInterface;
import com.gdsc.side.api.domain.Daily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    @Query(value = "select d.daily_id, d.title, dd.daily_status from daily_dates dd join daily d on dd.daily_id = d.daily_id where dd.dates like :date and d.user_id = :user_id order by dd.dates", nativeQuery = true)
    List<DailyMainResponseInterface> findDailyByDate(@Param("date") String date, @Param("user_id") Long user_id);

//    @Query(value = "select new com.gdsc.side.api.controller.dto.response.main.DailyMainResponseDto(d.dailyId, d.title, dd.dailyStatus, dd.date) from DailyDates dd join fetch Daily d where dd.date in :dates order by dd.date")
//    List<DailyMainResponseDto> findDailyByDate(@Param("dates") List<String> dates);
}
