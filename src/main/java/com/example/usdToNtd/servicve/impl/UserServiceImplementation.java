package com.example.usdToNtd.servicve.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usdToNtd.servicve.UserService;
import com.example.usdToNtd.vo.Raw;
import com.example.usdToNtd.vo.User;
import com.example.usdToNtd.vo.UserRepository;
import com.google.gson.Gson;


@Service
public class UserServiceImplementation implements UserService{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) throws Exception{


        BufferedReader reader = null;
        try {
            URL url = new URL("https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates");
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read); 
    
           String jsonStr=buffer.toString();
           System.out.println(jsonStr);

           Gson  gson=new Gson();
          // Type userListType =new TypeToken<ArrayList<Raw>>(){}.getType();

         Raw[] userArray=gson.fromJson(jsonStr, Raw[].class);
  
           for(Raw r :userArray){
                System.out.println(r.getUsdNtd());

                User u2=new User();
              // user
              u2.setDate(r.getDate());
              u2.setUsdToNtd(r.getUsdNtd());
              userRepository.save(u2);
           }


        } finally {
            if (reader != null)
                reader.close();
        }


        
        //return userRepository.save(user);
        return null;
    }

 

    /*public User registerUser(User user) throws Exception 
    {
        ObjectMapper mapper=new ObjectMapper();
        TypeReference<List<User>> typeReference=new TypeReference<List<User>>() {};
        InputStream inputStream=TypeReference.class.getResourceAsStream("https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates");

        List<User> users= mapper.readValue(inputStream, typeReference);
        for( User u:users){
            System.out.println(u.getUsdToNtd());
        }

        if(user!=null)
        {
            return userRepository.save(user);
        }
        throw new Exception("User is null");
    }*/

    @Override
    public List<User> getRegisteredUsers() throws Exception 
    {
        List<User>users=userRepository.findAll();
        if(users!=null)
        {
            return users;
        }
        throw new Exception("User is null");
    }

}
