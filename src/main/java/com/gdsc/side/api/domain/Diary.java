package com.gdsc.side.api.domain;

import com.gdsc.side.api.controller.dto.request.diary.DiaryRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
uniqueConstraints = {
        @UniqueConstraint(columnNames = "GOAL_ID")
})

public class Diary extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MYSQL, auto_increment
    @Column(name = "DIARY_ID")
    private Long diaryId;
    private String content;

    // 1 : 1 관계에서 보조테이블(자식)이 fk 갖게.(주 테이블은 뭔가 null이 들어가야되서 꺼림칙...)
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "GOAL_ID")
    private Goal goal;

    @Builder
    private Diary(String content, Goal goal) {
        this.content = content;
        this.goal = goal;


    }

    /**
     * 다이어리 생성
     */
    public static Diary createDiary(DiaryRequestDto diaryRequestDto, Goal goal){
        return Diary.builder()
                .content(diaryRequestDto.getContent())
                .goal(goal)
                .build();
    }

    /**
     * 다이어리 수정
     */
    public void updateDiary(DiaryRequestDto diaryRequestDto){
        this.content = diaryRequestDto.getContent();

    }

}
