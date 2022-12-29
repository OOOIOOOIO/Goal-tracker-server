package com.gdsc.side.api.controller.dto.response.main;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainResponseDto {

    private HashMap<String, List<?>> dailyResponseList;
    private HashMap<String, List<?>> goalResponseList;

    @Builder
    @JsonCreator
    public MainResponseDto(HashMap<String, List<?>> dailyResponseList, HashMap<String, List<?>> goalResponseList) {
        this.dailyResponseList = dailyResponseList;
        this.goalResponseList = goalResponseList;
    }
}
