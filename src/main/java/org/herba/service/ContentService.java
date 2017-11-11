package org.herba.service;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.herba.model.dto.ContentsInfo;
import org.herba.model.entity.Contents;
import org.herba.model.mapper.ContentsMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ContentService 文章服务类
 *
 * @author Varshonwood
 * @version 1.0
 * @date 17/11/07
 */
@Service
public class ContentService {

    /**
     * Field description
     */
    @Autowired
    ContentsMapper comtentsMapper;

    /**
     * deleteByPrimaryKey 根据ID删除文章
     *
     * @param cid
     */
    public void deleteByPrimaryKey(int cid) {
        comtentsMapper.deleteByPrimaryKey(cid);
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
     * selectPost   查询文章
     *
     * @param pageNo   页号
     * @param pageSize 页码
     * @return
     */
    public PageInfo selectPost(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<ContentsInfo> contentsInfoList = comtentsMapper.selectPost();
        PageInfo page = new PageInfo(contentsInfoList);
        return page;
    }

    /**
     * selectPage   查询页面
     *
     * @param pageNo   页号
     * @param pageSize 页码
     * @return
     */
    public PageInfo selectPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<ContentsInfo> contentsInfoList = comtentsMapper.selectPage();
        PageInfo page = new PageInfo(contentsInfoList);
        return page;
    }

    /**
     * selectPostByPrimaryKey   根据ID查询文章
     *
     * @param cid
     * @return
     */
    public ContentsInfo selectPostByPrimaryKey(int cid) {
        return comtentsMapper.selectPostByPrimaryKey(cid);
    }

    /**
     * selectPageByPrimaryKey   根据ID查询页面
     *
     * @param cid
     * @return
     */
    public ContentsInfo selectPageByPrimaryKey(int cid) {
        return comtentsMapper.selectPageByPrimaryKey(cid);
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
