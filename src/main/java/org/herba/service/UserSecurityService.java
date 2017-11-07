package org.herba.service;

import org.herba.model.entity.Users;
import org.herba.model.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserSecurityService implements UserDetailsService {
    @Autowired
    private UsersMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users temp = new Users();
        List<Users> users = userMapper.selectByParams(temp);
        if(users == null||users.size()==0){
            throw new UsernameNotFoundException("用户名不存在");
        }
        temp = users.get(0);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        if (temp.getRole()!=null){
            authorities.add(new SimpleGrantedAuthority(temp.getRole()));
            System.out.println("the login username = [" + temp.getName() + "]");
        }

        return new org.springframework.security.core.userdetails.User(temp.getName(),
                temp.getPassword(), authorities);
    }
}
