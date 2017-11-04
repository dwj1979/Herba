package org.herba.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.herba.model.entity.Contents;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ContentMapper {
        public Contents GetContentByCid(int cid);
}
