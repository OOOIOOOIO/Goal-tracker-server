package com.gdsc.side.api.service;

import com.gdsc.side.api.controller.dto.response.calender.CalenderResponseDto;
import com.gdsc.side.api.controller.dto.response.goal.GoalCompleteListResponseDto;
import com.gdsc.side.api.controller.dto.response.goal.GoalResponseDtoForCompleteListDto;
import com.gdsc.side.api.controller.dto.response.main.DailyCalenderResponseInterface;
import com.gdsc.side.api.domain.Goal;
import com.gdsc.side.api.domain.GoalStatus;
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

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final DailyDatesRepository dailyDatesRepository;
    private final DailyRepository dailyRepository;
    private final GoalRepository goalRepository;

    @Override
    public CalenderResponseDto completeDaily(String username, String month) {
        // user 조회
        Optional<User> user = userRepository.findByUsername(username);

        HashMap<String, List<?>> dailyResult = new HashMap<>();

        //날짜 추출
        List<String> dates = dailyDatesRepository.findDatesByMonth("%" + month + "%", user.get().getUserId());

        //daily
        for(String date : dates){
            List<DailyCalenderResponseInterface> dailyByDate =dailyRepository.findDailyByCompleteDate("%"+date+"%", user.get().getUserId()); // 에러나면 여기서 status

            dailyResult.put(date, dailyByDate);
        }

        return new CalenderResponseDto(dailyResult);
    }

    @Override
    public GoalCompleteListResponseDto completeGoals(String username) {
        // user 조회
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("user is not exist"));

        List<Goal> goals = goalRepository.findByUser_UserIdAndGoalStatus(user.getUserId(), GoalStatus.ON);

        List<GoalResponseDtoForCompleteListDto> collect = goals.stream()
                .map(g -> new GoalResponseDtoForCompleteListDto(g))
                .collect(Collectors.toList());


        return new GoalCompleteListResponseDto(collect);
    }


}
