package com.gdsc.side.service;

import com.gdsc.side.api.controller.dto.response.main.DailyMainResponseDto;
import com.gdsc.side.api.service.DailyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@SpringBootTest
public class DailyServiceImplTest {

    @Autowired
    private DailyService dailyService;

    @Test
    public void getDailyDatesMonthlyTest() throws Exception{
        //given
        List<String> dailyDatesMonthly = dailyService.getDailyDatesMonthly("2020-10");
        /*
        2020-10-10
        2020-10-11
        2020-10-12
         */

        //when 프린트해봥
        for(String date : dailyDatesMonthly){
            System.out.println("date = " + date);

        }

        //then
    }

//    @Test
//    public void getDailyMonthlyTest() throws Exception{
//        // When
//        List<DailyMainResponseDto> dailyMonthly = dailyService.getDailyMonthly("2020-10");
//
//
//        for(DailyMainResponseDto date : dailyMonthly){
//            System.out.println(date.getDailyId() +" "+ date.getTitle() +" "+date.getDailyStatus());
//        }
//    }
//
//    @Test
//    public void all() throws Exception{
//        //given
//        Map<String, List<?>> result = new HashMap<>();
//
//        List<String> dailyDatesMonthly = dailyService.getDailyDatesMonthly("2020-10");
//        /*
//        2020-10-10
//        2020-10-11
//        2020-10-12
//         */
//
//        for(String date : dailyDatesMonthly){
//            List<DailyMainResponseDto> dailyMonthly = dailyService.getDailyMonthly(date);
//
//            result.put("date", dailyMonthly);
//        }
//        //when 프린트해봥
//        for(String date : result.keySet()){
//            System.out.println(result.get(date));
//        }
//
//        //then
//    }
}
