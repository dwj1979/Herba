package org.herba.model.mapper;

import org.herba.model.entity.RelationshipsKey;

public interface RelationshipsMapper {
    int deleteByPrimaryKey(RelationshipsKey key);

    int insert(RelationshipsKey record);

    int insertSelective(RelationshipsKey record);
}