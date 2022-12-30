package com.gdsc.side.api.service;

import com.gdsc.side.api.controller.dto.request.goal.GoalRequestDto;
import com.gdsc.side.api.controller.dto.request.goal.GoalStatusChangeDto;
import com.gdsc.side.api.controller.dto.response.goal.GoalResponseDto;
import com.gdsc.side.api.controller.dto.response.main.GoalMainResponseInterface;
import com.gdsc.side.api.domain.Goal;
import com.gdsc.side.api.domain.User;
import com.gdsc.side.api.exception.type.GoalNotFoundException;
import com.gdsc.side.api.exception.type.UserNotFoundException;
import com.gdsc.side.api.repository.GoalRepository;
import com.gdsc.side.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class GoalServiceImpl implements GoalService{

    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    @Override
    public GoalResponseDto getGoalInfo(Long goalId) {
        // goal 조회
        Goal goal = goalRepository.findByGoalId(goalId).orElseThrow(() -> new GoalNotFoundException("goal is not exist"));


        return new GoalResponseDto(goal);
    }

    @Override
    public void saveGoal(GoalRequestDto goalRequestDto, String username) {
        // user 조회
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("user is not exist"));

        // Goal 생성
        Goal goal = Goal.createGoal(goalRequestDto, user);

        // 저장
        goalRepository.save(goal);
    }

    @Override
    public void updateGoal(GoalRequestDto goalRequestDto, Long goalId) {
        // goal 조회
        Goal goal = goalRepository.findByGoalId(goalId).orElseThrow(() -> new GoalNotFoundException("goal is not exist"));

        // dirty checking
        goal.updateGoal(goalRequestDto);

    }

    @Override
    public void deleteGoal(Long goalId) {
        // 삭제
        goalRepository.deleteById(goalId);
    }

    @Override
    public void updateGoalStatus(Long goalId, GoalStatusChangeDto goalStatusChangeDto) {
        // goal 조회
        Goal goal = goalRepository.findByGoalId(goalId).orElseThrow(() -> new GoalNotFoundException("goal is not exist"));

        // dirty checking
        goal.changeStatus(goalStatusChangeDto);

    }

    @Override
    public List<GoalMainResponseInterface> getGoalByMonth(String month, String username) {
        // user 조회
        Optional<User> user = userRepository.findByUsername(username);

        return goalRepository.findGoalByMonth("%" + month + "%", "%" + month + "%", user.get().getUserId());
    }
}
