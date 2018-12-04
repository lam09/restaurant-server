package com.mango.web.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class WriteCurrentTimeSchedule {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss");


    // initialDelay = 3 second.
    // fixedDelay = 2 second.
    @Scheduled( initialDelay = 0, fixedDelay = 60*60 * 1000)
    public void writeCurrentTime() {

        Date now = new Date();

        String nowString = df.format(now);

        System.out.println("Now is: "+ nowString);

    }
}
