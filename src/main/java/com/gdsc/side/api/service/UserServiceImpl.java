package com.gdsc.side.api.service;

import com.gdsc.side.api.controller.dto.response.calender.CalenderResponseListDto;
import com.gdsc.side.api.controller.dto.response.goal.GoalCompleteListResponseDto;
import com.gdsc.side.api.controller.dto.response.goal.GoalResponseDtoForCompleteListDto;
import com.gdsc.side.api.domain.Goal;
import com.gdsc.side.api.domain.GoalStatus;
import com.gdsc.side.api.domain.User;
import com.gdsc.side.api.exception.type.UserNotFoundException;
import com.gdsc.side.api.repository.DailyDatesRepository;
import com.gdsc.side.api.repository.GoalRepository;
import com.gdsc.side.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final GoalRepository goalRepository;

    @Override
    public CalenderResponseListDto completeDaily(String username) {
        // user 조회
        Optional<User> user = userRepository.findByUsername(username);



        return null;
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
