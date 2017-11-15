package org.herba.service;

import org.herba.model.entity.Metas;
import org.herba.model.mapper.MetasMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaService {
    @Autowired
    MetasMapper metasMapper;

    public List<Metas> selectAll() {
        return metasMapper.selectAll();
    }
    public int insertSelective(Metas record){
        return metasMapper.insertSelective(record);
    }


}
