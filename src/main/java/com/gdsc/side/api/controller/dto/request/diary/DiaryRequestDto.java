package com.gdsc.side.api.controller.dto.request.diary;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.gdsc.side.api.domain.Diary;
import com.gdsc.side.api.domain.Goal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryRequestDto {
    private String content;


    @JsonCreator
    public DiaryRequestDto(String content) {
        this.content = content;
    }
}
