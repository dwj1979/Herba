package org.herba.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.herba.model.entity.Users;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    public Users findByUserName(String username);
}
