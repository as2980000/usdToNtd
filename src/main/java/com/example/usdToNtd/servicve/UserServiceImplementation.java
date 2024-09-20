package com.example.usdToNtd.servicve;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usdToNtd.vo.User;
import  com.example.usdToNtd.vo.UserRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


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
           Type userListType =new TypeToken<ArrayList<User>>(){}.getType();

            User[] userArray=gson.fromJson(jsonStr, User[].class);
  
           for(User u :userArray){
                System.out.println(u.getId());
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
