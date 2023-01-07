package com.gdsc.side.api.service;

import com.gdsc.side.api.controller.dto.response.main.DailyMainResponseInterface;
import com.gdsc.side.api.controller.dto.response.main.GoalMainResponseInterface;
import com.gdsc.side.api.controller.dto.response.main.MainResponseDto;
import com.gdsc.side.api.domain.Daily;
import com.gdsc.side.api.domain.DailyDates;
import com.gdsc.side.api.domain.User;
import com.gdsc.side.api.exception.type.DailyNotFoundException;
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



    public void checkAndSaveDailyDates(Long dailyId, LocalDate date) {



    }


    /**
     * daily & goal 조회
     * Main > Main 접속 시 daily에 해당하는 dailyDates가 있는지 확인 후
     * 없다면 save
     * 있다면 pass
     */
    public MainResponseDto getDailyAndGoalByDate(LocalDate today, String username){
        // user 조회
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("user is not exist"));

        HashMap<String, List<?>> dailyResult = new HashMap<>();
        HashMap<String, List<?>> goalResult = new HashMap<>();

        //daily 추출
        for (Daily daily : dailyRepository.findAllByUser_UserId(user.getUserId())) {
            // dailyDates 조회
            Optional<DailyDates> dailyDates = dailyDatesRepository.findDailyStatusByDaily_DailyIdAndDate(daily.getDailyId(), today);

            // daiily에 해당하는 dates가 없을 경우(비어있을 경우) : 첫 등록
            if(dailyDates.isEmpty()){

                // dailyDates 생성
                DailyDates findDailyDates = DailyDates.createDailyDates("OFF", daily, today);

                // 저장
                dailyDatesRepository.save(findDailyDates);
            }
        }

        List<DailyMainResponseInterface> dailies = dailyRepository.findDailiesByDailyDates(today, user.getUserId());

        // daily 저장
        dailyResult.put("daily", dailies);

        //goal 추출
        List<GoalMainResponseInterface> goalByMonth = goalRepository.findGoalByDate(LocalDate.now(), user.getUserId());

        // goal 저장
        goalResult.put("goal", goalByMonth);

        return new MainResponseDto(dailyResult, goalResult);
    }

    /**
     * goal 날짜 추출
     */
    private List<LocalDate> getDatesBetweenTwoDates(LocalDate startDate, LocalDate endDate) {

        return startDate.datesUntil(endDate)
                .collect(Collectors.toList());
    }


}
