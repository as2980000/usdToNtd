package com.example.usdToNtd.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.usdToNtd.servicve.ExchangeService;

@Component
public class DailyTask {
    
    @Autowired   
    private ExchangeService exchangeService;

    @Scheduled(fixedDelay = 10 * 1000, initialDelay= 10 * 1000)
    public void everyTenSecondTask(){
        System.out.println("Ten second task.");
    }

    @Scheduled(cron = "0 0 18 * * ?", zone="Asia/Taipei")
    public void everyDayTask() throws Exception{
        System.out.println("Every day task.");
        try {
            exchangeService.syncExchange();
        } catch (Exception e) {         
            throw e;
        }

    }
}
