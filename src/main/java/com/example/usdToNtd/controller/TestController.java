package com.example.usdToNtd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.usdToNtd.servicve.UserService;

import  com.example.usdToNtd.vo.User;
import com.example.usdToNtd.vo.UserReq;

@RestController
public class TestController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/testPost")
    public ResponseEntity<User>register(@RequestBody UserReq user) throws Exception
    {
        System.out.println(user.getCurrency());
        System.out.println(user.getStartDate());
        return null;
    }
    //Registers the users
    @GetMapping("/register")
    public ResponseEntity<User>register() throws Exception
    {
       /* 
       @RequestBody User user
       if(user!=null)
        {
            userService.registerUser(user);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }*/

       
        userService.registerUser(null);
        return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        
    }

    //Get all the registered users.
    @GetMapping("/users")
    public ResponseEntity<List<User>>getRegisteredUser() throws Exception
    {
        List<User>users=userService.getRegisteredUsers();
        System.out.println("123");
        if(users!=null)
        {
            return new ResponseEntity<List<User>>(users,HttpStatus.OK);
        }
        return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*@GetMapping("/exchange")
    public ResponseEntity<List<User>>getRegisteredUser() throws Exception
    {
        List<User>users=userService.getRegisteredUsers();
        System.out.println("123");
        if(users!=null)
        {
            return new ResponseEntity<List<User>>(users,HttpStatus.OK);
        }
        return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
}
