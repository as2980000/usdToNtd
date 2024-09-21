package com.example.usdToNtd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.usdToNtd.servicve.ExchangeService;
import com.example.usdToNtd.servicve.UserService;
import com.example.usdToNtd.vo.User;
import com.example.usdToNtd.vo.UserReq;

@RestController
public class DemoController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    //@Qualifier("exchangeServiceImpl")
    private ExchangeService exchangeService;

    @GetMapping("/syncExchangeRate")
    public void  syncExchangeRate() throws Exception{

        System.out.println("SYNC Exchange Rate");
        exchangeService.syncExchange();

    }

    @PostMapping("/queryExchangeRate")
    public ResponseEntity<User>register(@RequestBody UserReq user) throws Exception
    {
        System.out.println(user.getCurrency());
        System.out.println(user.getStartDate());
       
        return null;
    }

}
