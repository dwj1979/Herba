package org.herba.service;

import java.util.List;

import com.github.pagehelper.PageHelper;
import org.herba.model.dto.ContentsInfo;
import org.herba.model.entity.Contents;
import org.herba.model.mapper.ContentsMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ContentService 文章服务类
 *
 * @version        1.0
 * @author         Varshonwood
 * @date           17/11/07
 */
@Service
public class ContentService {

    /** Field description */
    @Autowired
    ContentsMapper comtentsMapper;

    /**
     * deleteByPrimaryKey 根据ID删除文章
     *
     * @param cid
     */
    public void deleteByPrimaryKey(int cid) {
        comtentsMapper.selectByPrimaryKey(cid);
    }

    /**
     * insert   插入文章所有字段
     *
     * @param record
     */
    public void insert(Contents record) {
        comtentsMapper.insert(record);
    }

    /**
     * insertSelective  插入文章部分字段
     *
     * @param record
     */
    public void insertSelective(Contents record) {
        comtentsMapper.insertSelective(record);
    }

    /**
     * selectByPage    根据分页和类型查询文章
     *
     * @return
     * @param pageNo 页号
     * @param pageSize  页码
     * @param type  文章类型
     */
    public List<ContentsInfo> selectByType(int pageNo, int pageSize, String type) {
        PageHelper.startPage(pageNo, pageSize);
        return comtentsMapper.selectByType(type);
    }

    /**
     * selectByPrimaryKey   根据ID更新除内容以外的其他字段
     *
     * @param cid
     *
     * @return
     */
    public Contents selectByPrimaryKey(int cid) {
        return comtentsMapper.selectByPrimaryKey(cid);
    }

    /**
     * updateByPrimaryKeySelective  根据ID更新文章部分字段
     *
     * @param record
     */
    public void updateByPrimaryKeySelective(Contents record) {
        comtentsMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * updateByPrimaryKeyWithBLOBs
     *
     * @param record
     */
    public void updateByPrimaryKeyWithBLOBs(Contents record) {
        comtentsMapper.updateByPrimaryKeyWithBLOBs(record);
    }
}
//~ Formatted by Jindent --- http://www.jindent.com
