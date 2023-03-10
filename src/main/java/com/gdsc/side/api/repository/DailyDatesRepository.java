package com.gdsc.side.api.repository;

import com.gdsc.side.api.domain.DailyDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyDatesRepository extends JpaRepository<DailyDates, Long> {

    /**
     * dailyDates 단건 조회
     * fk로 조회하는 법
     * findBy + 외래키 주인 entity + _ + 외래키 주인의 식별자 필드명
     * dailyDates의 status 조회
     */
//    @Query("select dd from DailyDates dd where dd.daily.dailyId = :dailyId and dd.date = :date")
    Optional<DailyDates> findDailyStatusByDaily_DailyIdAndDate(Long dailyId, LocalDate date);


    /**
     * 1달치 dailyDates의 날짜 조회
     * JPQL은 where 절에 String만 될까..
     * LocalDateTime은 왜 안될까...ㅠ
     */
//    @Query(value = "select distinct dd.date from DailyDates dd join Daily d on dd.daily.dailyId = d.dailyId where dd.date like :month order by dd.date", nativeQuery = true)
    @Query(value = "select distinct dd.dates from daily_dates dd join daily d on dd.daily_id = d.daily_id where dd.dates like :month and d.user_id = :user_id order by dd.dates", nativeQuery = true)
    List<String> findDatesByMonth(@Param("month") String month, @Param("user_id") Long user_id);

}
