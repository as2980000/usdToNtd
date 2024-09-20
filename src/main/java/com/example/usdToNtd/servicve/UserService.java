package com.example.usdToNtd.servicve;

import java.util.List;

import  com.example.usdToNtd.vo.User;

public interface UserService {
    public User registerUser(User user) throws Exception;
    public List<User>getRegisteredUsers() throws Exception;
}
