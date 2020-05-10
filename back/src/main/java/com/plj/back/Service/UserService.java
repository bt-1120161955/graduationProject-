package com.plj.back.Service;

import com.plj.back.Entities.User;
import com.plj.back.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;


    public User getUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public boolean isExist(String username) {
        User user = getUserByUsername(username);
        return user != null;
    }

    public Integer addUser(User user) {
        return  userMapper.insertUser(user);
    }
}
