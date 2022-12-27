package com.gdsc.side.api.domain;

import com.gdsc.side.api.controller.dto.request.daily.DailyDatesStatusChangeDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyDates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MYSQL, auto_increment
    private Long dailyDatesId;

    @Column(name = "dates")
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private DailyStatus dailyStatus; /** enum? boolean? */

    // fk DAILY_ID, 다 대 일
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DAILY_ID")
    private Daily daily;

    @Builder
    private DailyDates(LocalDate date, DailyStatus dailyStatus, Daily daily) {
        this.date = date;
        this.dailyStatus = dailyStatus;
        this.daily = daily;
        
        // 연관관계 편의
        daily.getDailyDatesList().add(this);

    }

    /**
     * 단기 목표 날짜 생성
     *
     */
    public static DailyDates createDailyDates(String status, Daily daily, LocalDate date){
        return DailyDates.builder()
                .date(date)
                .dailyStatus(status.equals("ON") ? DailyStatus.ON : DailyStatus.OFF)
                .daily(daily)
                .build();

    }

    /**
     * 단기 목표 날짜 상태 수정
     * dirty checking 잘 되나 확인
     */
    public void changeStatus(DailyDatesStatusChangeDto changeDto){
        if(changeDto.getDailyStatusChange().equals("ON")){
            this.dailyStatus = DailyStatus.ON;
        }else{
            this.dailyStatus = DailyStatus.OFF;
        }
    }
}
