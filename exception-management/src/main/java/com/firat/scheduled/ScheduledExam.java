package com.firat.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledExam {

    // * * * * *

    @Scheduled(cron = "*/5 * * * * *")  //  5 saniyede bir çalışacak
    public void writeOnetoTen() {
        for (int i = 0; i <= 10; i++) {

            System.out.print(i + " - ");

        }


    }

}
