package com.example.usdToNtd.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.ext.DefaultHandler2;

import com.example.usdToNtd.servicve.ExchangeService;
import com.example.usdToNtd.vo.ExchangeData;
import com.example.usdToNtd.vo.Message;
import com.example.usdToNtd.vo.QueryResult;
import com.example.usdToNtd.vo.UserReq;

import ch.qos.logback.core.util.Duration;

@RestController
public class DemoController {
    
  
    
    @Autowired
    //@Qualifier("exchangeServiceImpl")
    private ExchangeService exchangeService;


    @GetMapping("/syncExchangeRate")
    public void  syncExchangeRate() throws Exception{

        System.out.println("SYNC Exchange Rate");
        exchangeService.syncExchange();

    }

    public static long calDays(String yyyymmdd1,String yyyymmdd2){

        SimpleDateFormat sdf= new SimpleDateFormat("yyyymmdd");
        Date d1;
        Date d2;
        try {
            d1 = sdf.parse(yyyymmdd1);
            d2=sdf.parse(yyyymmdd2);
            long diff=d2.getTime()-d1.getTime();
            long days=TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
            return days;
        } catch (ParseException e) {
            //e.printStackTrace();
        }

        return 1000L;
    }

    @PostMapping("/queryExchangeRate")
    public ResponseEntity<QueryResult> queryExchangeRate(@RequestBody UserReq userReq) throws Exception {
        QueryResult queryResult = new QueryResult();
        try {

            System.out.println("currency >>" + userReq.getCurrency());
            String convertStartDate = userReq.getStartDate().replaceAll("-", "").replaceAll("/", "");
            String convertEndDate = userReq.getEndDate().replaceAll("-", "").replaceAll("/", "");
            System.out.println("start date >>" + convertStartDate);
            System.out.println("end date >>" + convertEndDate);
            userReq.setStartDate(convertStartDate);
            userReq.setEndDate(convertEndDate);

            long days=calDays(convertStartDate,convertEndDate);
            if(days>365L){
                throw new Exception("日期區間不符");
            }

            Message error = new Message();
            error.setCode("0000");
            error.setMessage("成功");
            queryResult.setError(error);
            //List<ExchangeData> dataList=exchangeService.getAllData();
            List<ExchangeData> dataList = exchangeService.queryExchange(userReq);
            List<Map<String, Object>> filterList = new ArrayList<Map<String, Object>>();
            for (ExchangeData exd : dataList) {

                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("date", exd.getDate());
                map.put("usd", exd.getUsdToNtd());
                filterList.add(map);
            }
            queryResult.setCurrency(filterList);

            return new ResponseEntity<QueryResult>(queryResult, HttpStatus.OK);

        } catch (Exception e) {

            Message error = new Message();
            error.setCode("E001");
            error.setMessage(e.toString());
            queryResult.setError(error);
            queryResult.setCurrency(null);

            return new ResponseEntity<QueryResult>(queryResult, HttpStatus.OK);
        }
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
