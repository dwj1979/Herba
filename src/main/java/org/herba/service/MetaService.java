package org.herba.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.herba.model.dto.ContentsInfo;
import org.herba.model.entity.Metas;
import org.herba.model.mapper.MetasMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaService {
    @Autowired
    MetasMapper metasMapper;

    public List<Metas> selectAllCategorys() {
        Metas metas = new Metas();
        metas.setType("category");
        return metasMapper.selectByKey(metas);
    }

    /**
     * selectCategorysByPage   分页查询分类
     *
     * @param pageNo   页号
     * @param pageSize 页码
     * @return
     */
    public PageInfo selectCategoryByPage(int pageNo, int pageSize) {
        Metas metas = new Metas();
        metas.setType("category");
        PageHelper.startPage(pageNo, pageSize);
        List<Metas> metasList = metasMapper.selectByKey(metas);
        PageInfo page = new PageInfo(metasList);
        return page;
    }

    /**
     * selectCategorysByPageAndParent   通过父id分页查询分类
     *
     * @param pageNo   页号
     * @param pageSize 页码
     * @return
     */
    public PageInfo selectCategorysByPageAndParent(int pageNo, int pageSize, int parent) {
        Metas metas = new Metas();
        metas.setType("category");
        metas.setParent(parent);
        PageHelper.startPage(pageNo, pageSize);
        List<Metas> metasList = metasMapper.selectByKey(metas);
        PageInfo page = new PageInfo(metasList);
        return page;
    }

    /**
     * selectCategoryBymid   通过id查询分类
     *
     * @return
     */
    public Metas selectCategoryBymid(int mid) {
        return metasMapper.selectByPrimaryKey(mid);
    }

    public List<Metas> selectAllTags() {
        Metas metas = new Metas();
        metas.setType("tag");
        return metasMapper.selectByKey(metas);
    }

    public List<Metas> selectAll() {
        return metasMapper.selectByKey(new Metas());
    }

    public int insertSelective(Metas record) {
        return metasMapper.insertSelective(record);
    }

    public int updateMetas(Metas record) {
        return metasMapper.updateByPrimaryKeySelective(record);
    }


}
