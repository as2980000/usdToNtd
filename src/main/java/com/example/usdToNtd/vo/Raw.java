package com.example.usdToNtd.vo;

import com.google.gson.annotations.SerializedName;

public class Raw {
    
    private  Long id;
    private String Date;

    @SerializedName(value="USD/NTD")
    private String usdNtd;

    
    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsdNtd() {
        return usdNtd;
    }

    public void setUsdNtd(String usdNtd) {
        this.usdNtd = usdNtd;
    }
    
}
