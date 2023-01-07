package com.gdsc.side.api.repository;

import com.gdsc.side.api.controller.dto.response.main.DailyCalenderResponseInterface;
import com.gdsc.side.api.controller.dto.response.main.DailyMainResponseInterface;
import com.gdsc.side.api.domain.Daily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyRepository extends JpaRepository<Daily, Long> {

    /**
     * daily 단건 조회
     */
    Optional<Daily> findByDailyId(Long dailyId);

    /**
     * daily all 조회
     */
    List<Daily> findAllByUser_UserId(Long userId);

    /**
     * 1달치 daily 가져오기(Main)
     */
//    @Query(value = "select d.daily_id, d.title, dd.daily_status from daily_dates dd join daily d where d.user_id = :user_id order by dd.dates", nativeQuery = true)
//    List<DailyMainResponseInterface> findDailiesByDailyId(@Param("user_id") Long user_id);

    /**
     * 모든 daily 가져오기(Main)
     */
    @Query(value = "select d.daily_id, d.title, d.alert_dates, dd.daily_status, d.alert_time d.alert_status from daily d join daily_dates dd on dd.daily_id = d.daily_id where dd.dates =:today and d.user_id = :user_id", nativeQuery = true)
    List<DailyMainResponseInterface> findDailiesByDailyDates(@Param("today")LocalDate today, @Param("user_id") Long user_id);


    /***
     * 1달치 성공한 daily 데이터 가져오기(Calender)
     */
    @Query(value = "select d.daily_id, d.title, dd.daily_status from daily_dates dd join daily d on dd.daily_id = d.daily_id where dd.dates like :date and d.user_id = :user_id and dd.daily_status = :dailyStatus order by dd.dates", nativeQuery = true)
    List<DailyCalenderResponseInterface> findDailyByCompleteDate(@Param("date") String date, @Param("user_id") Long user_id,@Param("dailyStatus") String dailyStatus);

//    @Query(value = "select new com.gdsc.side.api.controller.dto.response.main.DailyMainResponseDto(d.dailyId, d.title, dd.dailyStatus, dd.date) from DailyDates dd join fetch Daily d where dd.date in :dates order by dd.date")
//    List<DailyMainResponseDto> findDailyByDate(@Param("dates") List<String> dates);
}
