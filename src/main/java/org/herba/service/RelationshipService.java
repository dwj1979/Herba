package org.herba.service;

import org.herba.model.entity.Relationships;
import org.herba.model.mapper.RelationshipsMapper;
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
