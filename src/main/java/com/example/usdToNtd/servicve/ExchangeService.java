package com.example.usdToNtd.servicve;

public interface ExchangeService {
    
    /*取得外匯成交資料,並將每日的美元/台幣 欄位(USD/NTD)資料與日期(yyyy-MM-dd HH:mm:ss) insert 至 table/collection, */
    public void syncExchange() throws Exception;
    
    /*取出日期區間內美元/台幣的歷史資料*/
    public void queryExchange() throws Exception;
}
