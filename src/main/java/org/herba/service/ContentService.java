package org.herba.service;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.herba.model.dto.ContentsInfo;
import org.herba.model.entity.Contents;
import org.herba.model.entity.Metas;
import org.herba.model.entity.Relationships;
import org.herba.model.mapper.ContentsMapper;

import org.herba.model.mapper.MetasMapper;
import org.herba.model.mapper.RelationshipsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    RelationshipsMapper relationshipsMapper;
    @Autowired
    MetasMapper metasMapper;

    /**
     * deleteByPrimaryKey 根据ID删除文章
     *
     * @param cid
     */
    public void deleteByPrimaryKey(int cid) {
        comtentsMapper.deleteByPrimaryKey(cid);
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

    @Transactional(rollbackFor = Exception.class)
    public int savePost(Contents contents, int categorysKey, List<Metas> tags) {
        int code = 0;
        //新增标签
        List<Metas> addTags = new ArrayList<Metas>();
        //目前文章所含标签关系
        List<Relationships> relationshipsList = new ArrayList<>();
        //目前文章所含标签关系
        List<Relationships> addRelationshipsList = new ArrayList<>();
        try {
            //更新文章信息
            comtentsMapper.updateByPrimaryKeySelective(contents);
            //提取新增标签
            for (Metas tag : tags) {
                if (tag.getMid() == null) {
                    addTags.add(tag);
                }
            }
            //给新增加的标签添加mid
            if (addTags.size() !=0) {
                for (Metas tag : addTags) {
                    int mid = metasMapper.insertSelective(tag);
                    addRelationshipsList.add(new Relationships(contents.getCid(), tag.getMid()));
                }
            }
            //提取出目前文章所含标签关系
            for (Metas tag : tags) {
                relationshipsList.add(new Relationships(contents.getCid(), tag.getMid()));
            }
            //提取目前文章所含分类关系
            Relationships relationships = new Relationships(contents.getCid(), categorysKey);
            //删除未关联标签信息
            relationshipsMapper.deleteTagMultiple(relationshipsList);
            //插入标签关联
            if (addRelationshipsList.size() !=0){
                relationshipsMapper.insertMultiple(addRelationshipsList);
            }
            //更新分类信息
            relationshipsMapper.updateCategoryByPrimaryKey(relationships);
            code = 200;
        } catch (Exception e) {
            e.printStackTrace();
            code = 204;
        }

        return code;
    }

}
//~ Formatted by Jindent --- http://www.jindent.com
