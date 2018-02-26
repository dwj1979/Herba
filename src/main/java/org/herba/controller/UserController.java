package org.herba.controller;

import org.herba.model.entity.Users;
import org.herba.service.UserSecurityService;
import org.herba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/admin/register"})
    public void register(@RequestBody Users user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.register(user);
    }
    @RequestMapping(value = {"/admin/me"})
    public Users me() {
        Users principal =new Users();
        String name=SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if(!name.equals("")){
            principal= userService.selectByUsername(name);
            return  principal;
        }else {
            return null;
        }
    }
}
