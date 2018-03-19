package com.herba.service;

import com.herba.model.entity.Users;
import com.herba.model.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UsersMapper usersMapper;

    public void register(Users users) {
        usersMapper.insertSelective(users);
    }
    public Users selectByUsername(String username) {
        return  usersMapper.selectByParams(new Users(username)).get(0);
    }
}
