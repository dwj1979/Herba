package com.herba.service;

import com.herba.model.entity.Relationships;
import com.herba.model.mapper.RelationshipsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationshipService {
    @Autowired
    RelationshipsMapper relationshipsMapper;

    public int deleteByPrimaryKey(int key) {
        return relationshipsMapper.deleteByPrimaryKey(key);
    }

    public int insert(Relationships record) {
        return relationshipsMapper.insert(record);
    }
}
