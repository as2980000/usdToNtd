package com.example.usdToNtd.vo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "exchange")
public class User {
    
    @Id
    private String id;

    @Field("Date")
    private String date;

    @Field("USD/NTD")
    private String  usdToNtd;

    
    public String getUsdToNtd() {
        return usdToNtd;
    }

    public void setUsdToNtd(String usdToNtd) {
        this.usdToNtd = usdToNtd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



}
