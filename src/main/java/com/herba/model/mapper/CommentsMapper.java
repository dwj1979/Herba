package com.herba.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.herba.model.entity.Comments;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface CommentsMapper {
    int deleteByPrimaryKey(Integer coid);

    int insert(Comments record);

    int insertSelective(Comments record);

    Comments selectByPrimaryKey(Integer coid);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKeyWithBLOBs(Comments record);

    int updateByPrimaryKey(Comments record);

    List<Comments> selectComment();
}