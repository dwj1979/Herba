package org.herba.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.herba.model.entity.Relationships;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RelationshipsMapper {
    int deleteByPrimaryKey(int key);

    int insert(Relationships record);

    int insertMultiple(List<Relationships> relationshipsList);

    int updateCategoryByPrimaryKey(Relationships relationships);

    int deleteTagMultiple(List<Relationships> relationships);
}