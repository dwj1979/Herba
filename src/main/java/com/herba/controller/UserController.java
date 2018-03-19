package com.herba.controller;

import com.herba.exception.ExitUserNameException;
import com.herba.model.dto.DataResponse;
import com.herba.model.dto.ResponseCode;
import com.herba.model.entity.Users;
import com.herba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *  register 注册用户
     *
     * */
    @RequestMapping(value = {"/admin/register"})
    public DataResponse register(@RequestBody Users user) {
        if(userService.selectByUsername(user.getName()).getUid()>0){
            throw new ExitUserNameException("用户名已存在");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.register(user);
        return new DataResponse(ResponseCode.SUCCESS.getCode(), "注册用户成功", null);

    }

    @RequestMapping(value = {"/admin/me"})
    public DataResponse me() {
        Users principal = new Users();
        String name = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (!name.equals("")) {
            principal = userService.selectByUsername(name);
            return new DataResponse(ResponseCode.SUCCESS.getCode(), "", principal);
        } else {
            return new DataResponse(ResponseCode.FAIL.getCode(), "获取用户信息失败", null);
        }
    }
}
