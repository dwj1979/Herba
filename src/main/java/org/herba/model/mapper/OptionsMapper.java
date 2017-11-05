package org.herba.model.mapper;

import org.herba.model.entity.Options;
import org.herba.model.entity.OptionsKey;

public interface OptionsMapper {
    int deleteByPrimaryKey(OptionsKey key);

    int insert(Options record);

    int insertSelective(Options record);

    Options selectByPrimaryKey(OptionsKey key);

    int updateByPrimaryKeySelective(Options record);

    int updateByPrimaryKeyWithBLOBs(Options record);
}