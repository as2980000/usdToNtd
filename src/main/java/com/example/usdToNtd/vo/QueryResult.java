package com.example.usdToNtd.vo;

import java.util.List;


public class QueryResult {
    
    Message error;
    List<ExchangeData> currency;

    public List<ExchangeData> getCurrency() {
        return currency;
    }

    public void setCurrency(List<ExchangeData> currency) {
        this.currency = currency;
    }

    public Message getError() {
        return error;
    }

    public void setError(Message error) {
        this.error = error;
    }





}
