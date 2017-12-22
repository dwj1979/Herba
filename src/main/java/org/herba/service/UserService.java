package org.herba.service;

import org.herba.model.entity.Users;
import org.herba.model.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UsersMapper usersMapper;

    public void register(Users users) {
        usersMapper.insertSelective(users);
    }
}
