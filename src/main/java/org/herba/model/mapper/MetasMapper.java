package org.herba.model.mapper;

import org.herba.model.entity.Metas;

public interface MetasMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(Metas record);

    int insertSelective(Metas record);

    Metas selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(Metas record);

    int updateByPrimaryKey(Metas record);
}