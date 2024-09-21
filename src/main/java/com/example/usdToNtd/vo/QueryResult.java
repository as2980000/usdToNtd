package com.example.usdToNtd.vo;

import java.util.List;
import java.util.Map;


public class QueryResult {
    
    Message error;
   // List<ExchangeData> dataList;
    List<Map<String,Object>> currency;


    public Message getError() {
        return error;
    }

    public void setError(Message error) {
        this.error = error;
    }


    public List<Map<String, Object>> getCurrency() {
        return currency;
    }

    public void setCurrency(List<Map<String, Object>> currency) {
        this.currency = currency;
    }





}
