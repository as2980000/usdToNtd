package com.example.usdToNtd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.usdToNtd.servicve.ExchangeService;
import com.example.usdToNtd.servicve.UserService;
import com.example.usdToNtd.vo.ExchangeData;
import com.example.usdToNtd.vo.Message;
import com.example.usdToNtd.vo.QueryResult;
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
    public ResponseEntity<QueryResult>queryExchangeRate(@RequestBody UserReq user) throws Exception
    {
        System.out.println(user.getCurrency());
        System.out.println(user.getStartDate());
       
        QueryResult queryResult=new QueryResult();
     
        
        Message error=new Message();
        error.setCode("0000");
        error.setMessage("成功");
        queryResult.setError(error);

        List<ExchangeData> dataList=exchangeService.getAllData();
        queryResult.setCurrency(dataList);

        return new ResponseEntity<QueryResult>(queryResult,HttpStatus.OK);
    }

    @GetMapping("/allExchanges")
    public ResponseEntity<List<ExchangeData>>getAllExchangeData() throws Exception
    {
        System.out.println("Get All Exchange Data");
        List<ExchangeData> dataList=exchangeService.getAllData();
  
        if(dataList!=null)
        {
            return new ResponseEntity<List<ExchangeData>>(dataList,HttpStatus.OK);
        }
        return new ResponseEntity<List<ExchangeData>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
