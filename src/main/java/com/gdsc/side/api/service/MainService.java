package com.gdsc.side.api.service;

import com.gdsc.side.api.controller.dto.response.main.DailyMainResponseInterface;
import com.gdsc.side.api.controller.dto.response.main.GoalMainResponseInterface;
import com.gdsc.side.api.controller.dto.response.main.MainResponseDto;
import com.gdsc.side.api.domain.Daily;
import com.gdsc.side.api.domain.User;
import com.gdsc.side.api.exception.type.UserNotFoundException;
import com.gdsc.side.api.repository.DailyDatesRepository;
import com.gdsc.side.api.repository.DailyRepository;
import com.gdsc.side.api.repository.GoalRepository;
import com.gdsc.side.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MainService {
    private final GoalRepository goalRepository;
    private final DailyRepository dailyRepository;
    private final DailyDatesRepository dailyDatesRepository;
    private final UserRepository userRepository;

    /**
     * daily & goal 조회
     */
    public MainResponseDto getDailyAndGoalByMonthly(String month, String username){
        // user 조회
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("user is not exist"));


        HashMap<String, List<?>> dailyResult = new HashMap<>();
        HashMap<String, List<?>> goalResult = new HashMap<>();

        //daily
        List<DailyMainResponseInterface> dailies = dailyRepository.findDailiesByDailyId(user.getUserId());

        dailyResult.put("daily", dailies);

        //goal
        List<GoalMainResponseInterface> goalByMonth = goalRepository.findGoalByMonth("%"+month+"%", "%"+month+"%", user.getUserId());

        for (GoalMainResponseInterface goal : goalByMonth) {

            List<LocalDate> datesBetweenTwoDates = getDatesBetweenTwoDates(goal.getCreated_at().toLocalDate(), goal.getEnd_date());

            // a ~ b 까지 날짜 추출
            for (LocalDate date : datesBetweenTwoDates) {
                // month에 해당하는 애들만
                if(date.toString().contains(month)){
                    // 처음일 때
                    if(goalResult.get(date.toString()) == null){
                        goalResult.put(date.toString(), goalByMonth);
                    }

                }
            }
        }

        return new MainResponseDto(dailyResult, goalResult);
    }
    private List<LocalDate> getDatesBetweenTwoDates(LocalDate startDate, LocalDate endDate) {

        return startDate.datesUntil(endDate)
                .collect(Collectors.toList());
    }


}
