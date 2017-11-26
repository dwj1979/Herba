package org.herba.service;

import org.herba.model.entity.Users;
import org.herba.model.mapper.UsersMapper;
import org.herba.security.FormUserDetails;
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
        Users temp = new Users(username);
        List<Users> users = userMapper.selectByParams(temp);
        if(users == null||users.size()==0){
            throw new UsernameNotFoundException("用户名不存在");
        }
        temp = users.get(0);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        if (temp.getGroup()!=null){
            authorities.add(new SimpleGrantedAuthority(temp.getGroup()));
        }

        return new FormUserDetails(temp.getUid(),temp.getName(),temp.getPassword(),true,authorities);
    }
}
