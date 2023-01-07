package com.gdsc.side.api.service;

import com.gdsc.side.api.controller.dto.request.daily.DailyDatesRequestDto;
import com.gdsc.side.api.controller.dto.request.daily.DailyDatesStatusChangeDto;
import com.gdsc.side.api.controller.dto.request.daily.DailyRequestDto;
import com.gdsc.side.api.controller.dto.response.daily.DailyResponseDto;
import com.gdsc.side.api.domain.Daily;
import com.gdsc.side.api.domain.DailyDates;
import com.gdsc.side.api.domain.User;
import com.gdsc.side.api.exception.type.DailyDatesNotFoundException;
import com.gdsc.side.api.exception.type.DailyNotFoundException;
import com.gdsc.side.api.exception.type.UserNotFoundException;
import com.gdsc.side.api.repository.DailyDatesRepository;
import com.gdsc.side.api.repository.DailyRepository;
import com.gdsc.side.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DailyServiceImpl implements DailyService{

    private final DailyRepository dailyRepository;
    private final DailyDatesRepository dailyDatesRepository;
    private final UserRepository userRepository;

    @Override
    public DailyResponseDto getDailyInfo(Long dailyId, LocalDate date) {

        // dailyDates의 dailyStatus 조회
        DailyDates dailyDates = dailyDatesRepository.findDailyStatusByDaily_DailyIdAndDate(dailyId, date).orElseThrow(() -> new DailyDatesNotFoundException("dailyDates is not exist"));

        // daily 조회
        Daily daily = dailyRepository.findByDailyId(dailyId).orElseThrow(() -> new DailyNotFoundException("daily is not exist"));

        return new DailyResponseDto(daily, String.valueOf(dailyDates.getDailyStatus()));
    }

    @Override
    public void saveDaily(DailyRequestDto dailyRequestDto, String username) {
        // user 조회
        User user = findUser(username);

        // daily 생성
        Daily daily = Daily.createDaily(dailyRequestDto, user);

        // 저장
        dailyRepository.save(daily);

    }

    @Override
    public void updateDaily(DailyRequestDto dailyRequestDto, Long dailyId) {
        // daily 조회
        Daily daily = dailyRepository.findByDailyId(dailyId).orElseThrow(() -> new DailyNotFoundException("daily is not exist"));

        // dirty checking
        daily.updateDaily(dailyRequestDto);


    }

    @Override
    public void deleteDaily(Long dailyId) {
        dailyRepository.deleteById(dailyId);

    }

    @Override
    public void saveDailyDates(Long dailyId, LocalDate date, DailyDatesRequestDto dailyDatesRequestDto) {
        // daily 조회
        Daily daily = dailyRepository.findByDailyId(dailyId).orElseThrow(() -> new DailyNotFoundException("daily is not exist"));

        // dailyDates 생성
        DailyDates dailyDates = DailyDates.createDailyDates(dailyDatesRequestDto.getDailyStatus(), daily, date);

        // 저장
        dailyDatesRepository.save(dailyDates);
    }

    /**
     *
     * 팝업 창 뜨기 전에 ON으로 할 경우
     * dailyId, date으로 status를 확인 해 없을 경우 save
     * 만약 있을 경우 그대로 update
     */
    @Override
    public void checkAndUpdateDailyDates(Long dailyId, LocalDate date, DailyDatesStatusChangeDto dailyDatesStatusChangeDto) {
        
        // dailyDates 조회
        Optional<DailyDates> dailyDates = dailyDatesRepository.findDailyStatusByDaily_DailyIdAndDate(dailyId, date);

        // 없을 경우(비어있을 경우) : 첫 등록
        if(dailyDates.isEmpty()){
            // daily 조회
            Daily daily = dailyRepository.findByDailyId(dailyId).orElseThrow(() -> new DailyNotFoundException("daily is not exist"));

            // dailyDates 생성
            DailyDates findDailyDates = DailyDates.createDailyDates(dailyDatesStatusChangeDto.getDailyStatusChange(), daily, date);

            // 저장
            dailyDatesRepository.save(findDailyDates);


        }else{
            // dirty checking
            dailyDates.get().changeStatus(dailyDatesStatusChangeDto);

        }

    }


    private User findUser(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("user is not exist"));
    }


}
