package com.herba.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.herba.model.entity.Metas;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MetasMapper { 
    int deleteByPrimaryKey(Integer mid);

//    int insert(Metas record);

    int insertSelective(Metas record);

    int insertSelectiveMultiple(List<Metas> record);

    Metas selectByPrimaryKey(Integer mid);

    List<Metas> selectByKey(Metas meta);

    int updateByPrimaryKeySelective(Metas record);
}