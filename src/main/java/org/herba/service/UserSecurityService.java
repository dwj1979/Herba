package org.herba.service;

import org.herba.model.entity.Users;
import org.herba.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserSecurityService implements UserDetailsService {
    @Autowired
    private  UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userMapper.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        if (user.getRole()!=null){
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
            System.out.println("the login username = [" + user.getName() + "]");
        }

        return new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(), authorities);
    }
}
