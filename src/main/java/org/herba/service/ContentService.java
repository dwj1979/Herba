package org.herba.service;

import org.herba.model.entity.Contents;
import org.herba.model.mapper.ContentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentService {

    @Autowired
    ContentsMapper contentsMapper;
    public Contents GetContentByCid(int cid){
        return contentsMapper.selectByPrimaryKey(cid);
    };


}
