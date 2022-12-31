package com.gdsc.side.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class DailyServiceImplTest {

//    @Autowired
//    private DailyService dailyService;
//
//    @Autowired
//    private GoalService goalService;
//
//    @Test
//    public void getDailyDatesMonthlyTest() throws Exception{
//        //given
//        List<String> dailyDatesMonthly = dailyService.getDatesByMonth("2020-10");
//        /*
//        2020-10-10
//        2020-10-11
//        2020-10-12
//        ...
//         */
//
//        for(String date : dailyDatesMonthly){
//            System.out.println("date = " + date);
//
//        }
//
//        //then
//    }
//
//    @Test
//    public void getDailyMonthlyTest() throws Exception{
//
//        final Map<String, List<?>> result = new HashMap<>();
//
//        // 날짜 가져오기
//        List<String> dates= dailyService.getDatesByMonth("2020-10");
//
//        // for문 돌면서 가져오기 하..LocalDate 타입이 왜...@Qquery에 안들어가지..
//
//
//
//        List<DailyMainResponseInterface> dailyByDate = dailyService.getDailyByDate("2020-10-11");
//
//        System.out.println(dailyByDate.get(0).getDaily_id());
//        System.out.println(dailyByDate.get(0).getTitle());
//        System.out.println(dailyByDate.get(0).getDaily_status());
//        System.out.println(dailyByDate.get(1).getDaily_id());
//        System.out.println(dailyByDate.get(1).getTitle());
//        System.out.println(dailyByDate.get(1).getDaily_status());
//
//    }
//
//    @Test
//    public void getGoalByMonth() throws Exception{
//        final Map<String, List<?>> result = new HashMap<>();
//
//        // 날짜 가져오기
////        List<String> dates= dailyService.getDatesByMonth("2020-10");
//
//        // for문 돌면서 가져오기 하..LocalDate 타입이 왜...@Qquery에 안들어가지..
//        List<GoalMainResponseInterface> goalByMonth = goalService.getGoalByMonth("2020-10");
//
//        for (GoalMainResponseInterface goal : goalByMonth) {
//            System.out.println(goal.getGoal_id());
//            System.out.println(goal.getTitle());
//            System.out.println(goal.getGoal_status());
//            System.out.println(goal.getCreated_at());
//            System.out.println(goal.getEnd_date());
//        }
//
//        // goal 돌려~
//        for (GoalMainResponseInterface goal : goalByMonth) {
//
//            List<LocalDate> datesBetweenTwoDates = getDatesBetweenTwoDates(goal.getCreated_at().toLocalDate(), goal.getEnd_date());
//            // a ~ b 까지 날짜 추출
//            for (LocalDate date : datesBetweenTwoDates) {
//                System.out.println(date);
//                // 처음
//                if(result.get(date.toString()) == null){
//                    result.put(date.toString(), goalByMonth);
//                }
//            }
//        }
//
//
//    }
//
//    private List<LocalDate> getDatesBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
//
//        return startDate.datesUntil(endDate)
//                .collect(Collectors.toList());
//    }

}
