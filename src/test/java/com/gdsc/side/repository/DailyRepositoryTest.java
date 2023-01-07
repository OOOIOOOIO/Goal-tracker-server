package com.gdsc.side.repository;

import com.gdsc.side.api.domain.Daily;
import com.gdsc.side.api.repository.DailyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
public class DailyRepositoryTest {

    @Autowired
    private DailyRepository dailyRepository;

    @Test
    public void findAllByUserId() throws Exception {

        //given
        List<Daily> allByUserId = dailyRepository.findAllByUser_UserId(1L);

        //when
        for (Daily daily : allByUserId) {
            System.out.println(daily.getDailyId());
            System.out.println(daily.getTitle());
            System.out.println(daily.getUser().getUserId());
            System.out.println("==============");
        }
        System.out.println(LocalDate.now());

        //then
        Assertions.assertThat(allByUserId.size()).isEqualTo(5);
    }
}
