package org.herba.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.herba.model.entity.Metas;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MetasMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(Metas record);

    int insertSelective(Metas record);

    int insertSelectiveMultiple(List<Metas> record);

    Metas selectByPrimaryKey(Integer mid);

    List<Metas> selectAll();

    int updateByPrimaryKeySelective(Metas record);

    int updateByPrimaryKey(Metas record);
}