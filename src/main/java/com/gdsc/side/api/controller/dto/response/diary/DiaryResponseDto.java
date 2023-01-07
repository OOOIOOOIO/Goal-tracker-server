package com.gdsc.side.api.controller.dto.response.diary;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.gdsc.side.api.domain.Diary;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryResponseDto {

    private Long diaryId;
    private String content;

    public DiaryResponseDto(Diary diary) {
        this.diaryId = diary.getDiaryId();
        this.content = diary.getContent();
    }
}
