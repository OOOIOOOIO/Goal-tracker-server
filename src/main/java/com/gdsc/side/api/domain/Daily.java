package com.gdsc.side.api.domain;

import com.gdsc.side.api.controller.dto.request.daily.DailyRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Daily extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MYSQL, auto_increment
    @Column(name = "DAILY_ID")
    private Long dailyId;

    private String title;

    private String alertDates;

    @Enumerated(EnumType.STRING)
    private AlertStatus alertStatus; /** enum? boolean? */

    private String alertTime;

    private String content;

    // fk USER_ID, 다 대 일
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    // 일 대 다 cascade,고아객체 생각해보기
    @OneToMany(mappedBy = "daily", cascade = CascadeType.REMOVE)
    private List<DailyDates> dailyDatesList = new ArrayList<>();

    @Builder
    private Daily(String title, String alertDates, AlertStatus alertStatus, String alertTime, String content, User user) {
        this.title = title;
        this.alertDates = alertDates;
        this.alertStatus = alertStatus;
        this.alertTime = alertTime;
        this.content = content;
        this.user = user;

    }

    /**
     * 단기 목표 생성
     */
    public static Daily createDaily(DailyRequestDto dailyRequestDto, User user){
        return Daily.builder()
                .title(dailyRequestDto.getTitle())
                .alertDates(dailyRequestDto.getAlertDates())
                .alertStatus(dailyRequestDto.getAlertStatus().equals("ON") ? AlertStatus.ON : AlertStatus.OFF)
                .alertTime(dailyRequestDto.getAlertTime())
                .content(dailyRequestDto.getContent())
                .user(user)
                .build();
    }

    /**
     * daily 목표 수정
     * update가 다 바뀌는지 null로 들어가는지 어떻게 되는지
     */
    public void updateDaily(DailyRequestDto dailyRequestDto){

        this.title = dailyRequestDto.getTitle();
        this.alertDates =dailyRequestDto.getAlertDates();
        this.alertStatus =dailyRequestDto.getAlertStatus().equals("ON") ? AlertStatus.ON : AlertStatus.OFF;
        this.alertTime =dailyRequestDto.getAlertTime();
        this.content =dailyRequestDto.getContent();


    }

}
