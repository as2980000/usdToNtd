package com.example.usdToNtd.servicve.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usdToNtd.servicve.ExchangeService;
import com.example.usdToNtd.vo.ExchangeDataRepository;
import com.example.usdToNtd.vo.Raw;
import com.example.usdToNtd.vo.User;
import com.google.gson.Gson;

@Service
public class ExchangeServiceImpl  implements  ExchangeService{

    @Autowired
    private ExchangeDataRepository exchangeDataRepository;
    
    @Override
    public void syncExchange() throws Exception {

        BufferedReader reader = null;
        try {
            URL url = new URL("https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates");
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }

            String jsonStr = buffer.toString();
            System.out.println(jsonStr);

            Gson gson = new Gson();
            // Type userListType =new TypeToken<ArrayList<Raw>>(){}.getType();

            Raw[] userArray = gson.fromJson(jsonStr, Raw[].class);

            for (Raw r : userArray) {
                System.out.println(r.getUsdNtd());
                User u2 = new User();
                // user
                u2.setDate(r.getDate());
                u2.setUsdToNtd(r.getUsdNtd());
                //userRepository.save(u2);
                
            }

        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        //throw new UnsupportedOperationException("Unimplemented method 'syncExchange'");
    }

    @Override
    public void queryExchange() throws Exception{
       
        //throw new UnsupportedOperationException("Unimplemented method 'queryExchange'");
    }
    
}
