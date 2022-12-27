package com.gdsc.side.api.domain;

import com.gdsc.side.api.controller.dto.request.goal.GoalRequestDto;
import com.gdsc.side.api.controller.dto.request.goal.GoalStatusChangeDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.FetchType.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Goal extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MYSQL, auto_increment
    @Column(name = "GOAL_ID")
    private Long goalId;

    private String title;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private AlertStatus alertStatus; /** enum? boolean? */

    private String alertTime;

    private String content;
    @Enumerated(EnumType.STRING)
    private GoalStatus goalStatus; /** enum? boolean */

    // fk USER_ID, 다 대 일
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne(mappedBy = "goal" ,fetch = LAZY, cascade = CascadeType.REMOVE)
    private Diary diary;


    @Builder
    private Goal(String title, LocalDate endDate, AlertStatus alertStatus, String alertTime, String content, GoalStatus goalStatus, User user) {
        this.title = title;
        this.endDate = endDate;
        this.alertStatus = alertStatus;
        this.alertTime = alertTime;
        this.content = content;
        this.goalStatus = goalStatus;
        this.user = user;

    }


    /**
     * 장기 목표 생성
     *
     */
    public static Goal createGoal(GoalRequestDto goalRequestDto, User user){
        return Goal.builder()
                .title(goalRequestDto.getTitle())
                .endDate(goalRequestDto.getEndDate())
                .alertStatus(goalRequestDto.getAlertStatus().equals("ON") ? AlertStatus.ON : AlertStatus.OFF)
                .alertTime(goalRequestDto.getAlertTime())
                .content(goalRequestDto.getContent())
                .goalStatus(goalRequestDto.getGoalStatus().equals("ON") ? GoalStatus.ON : GoalStatus.OFF)
                .user(user)
                .build();
    }

    /**
     * 장기 목표 수정
     */
    public void updateGoal(GoalRequestDto goalRequestDto){

        this.title = goalRequestDto.getTitle();
        this.endDate = goalRequestDto.getEndDate();
        this.alertStatus = goalRequestDto.getAlertStatus().equals("ON") ? AlertStatus.ON : AlertStatus.OFF;
        this.alertTime = goalRequestDto.getAlertTime();
        this.content = goalRequestDto.getContent();
        this.goalStatus = goalRequestDto.getGoalStatus().equals("ON") ? GoalStatus.ON : GoalStatus.OFF;

    }

    /**
     * goalStatus 변경
     * dirty checking 잘 되나 확인
     */
    public void changeStatus(GoalStatusChangeDto changeDto){
        if(changeDto.getGoalStatusChange().equals("ON")){
            this.goalStatus = GoalStatus.ON;
        }else {
            this.goalStatus = GoalStatus.OFF;
        }
    }
}
