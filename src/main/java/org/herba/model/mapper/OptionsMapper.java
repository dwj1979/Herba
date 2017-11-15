package org.herba.model.mapper;

import org.herba.model.entity.Options;

public interface OptionsMapper {
    int deleteByPrimaryKey(String key);

    int insert(Options record);

    Options selectByPrimaryKey(String key);

    int updateByPrimaryKeySelective(Options record);

}