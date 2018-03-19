package com.herba.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.herba.model.entity.Options;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface OptionsMapper {
    int deleteByPrimaryKey(String key);

    int insert(Options record);

    List<Options> selectAll();

}