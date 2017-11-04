package org.herba.service;

import org.herba.model.entity.Contents;
import org.herba.model.mapper.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentService {
    @Autowired
    ContentMapper contentMapper;
    public Contents GetContentByCid(int cid){
        return contentMapper.GetContentByCid(cid);
    };


}
