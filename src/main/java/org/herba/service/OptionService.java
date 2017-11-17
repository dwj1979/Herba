package org.herba.service;

import org.herba.model.entity.Options;
import org.herba.model.mapper.OptionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {
    @Autowired
    OptionsMapper optionsMapper;

    public List<Options> selectAll() {
        return optionsMapper.selectAll();
    }

}
