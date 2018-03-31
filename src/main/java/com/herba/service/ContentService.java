package com.herba.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.herba.model.dto.ContentsInfo;
import com.herba.model.entity.Contents;
import com.herba.model.entity.Metas;
import com.herba.model.entity.Relationships;
import com.herba.model.entity.Users;
import com.herba.model.mapper.ContentsMapper;

import com.herba.model.mapper.MetasMapper;
import com.herba.model.mapper.RelationshipsMapper;
import com.herba.model.mapper.UsersMapper;
import com.herba.spider.webmagic.WebMagicContents;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    UsersMapper usersMapper;

    /**
     * selectPost   查询文章
     *
     * @param pageNo   页号
     * @param pageSize 页码
     * @return
     */
    public PageInfo selectPost(int pageNo, int pageSize) {
        List<ContentsInfo> contentsInfoList;
        PageHelper.startPage(pageNo, pageSize);
        contentsInfoList = comtentsMapper.selectPost();
        PageInfo page = new PageInfo(contentsInfoList);
        return page;
    }

    /**
     * selectPost   根据meta查询文章信息
     *
     * @param pageNo        页号
     * @param pageSize      页码
     * @param relationships 关系
     * @return
     */
    public PageInfo selectPost(int pageNo, int pageSize, Relationships relationships) {
        List<ContentsInfo> contentsInfoList;
        PageHelper.startPage(pageNo, pageSize);
        contentsInfoList = comtentsMapper.selectPostByMeta(relationships);
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
     * savePostBySpider  爬虫保存文章信息
     *
     * @param webMagicContents 爬虫页面信息
     * @param catgory        分类ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void savePostByWebMagicSpider(WebMagicContents webMagicContents, Metas catgory) {
        //        转为标准Content模型
        Users users = new Users();
        users.setName("spider");
        users = usersMapper.selectByParams(users).get(0);
        Contents contents = new Contents(null,
                webMagicContents.getTitle(),
                null,
                (int) new Date().getTime(),
                (int) new Date().getTime(),
                0,
                users.getUid(),
                null,
                "post",
                "publish",
                null,
                0,
                "1",
                "1",
                "1",
                1,0,0, webMagicContents.getText());

        comtentsMapper.insertSelective(contents);
        contents.setSlug(contents.getCid().toString());
        //更新slug
        comtentsMapper.updateByPrimaryKeySelective(contents);
        if (catgory.getMid() == null) {
            metasMapper.insertSelective(catgory);
        }
        relationshipsMapper.insert(new Relationships(contents.getCid(), catgory.getMid()));
    }

    /**
     * savePost  保存文章信息
     *
     * @param contents     页面信息
     * @param categorysKey 分类ID
     * @param tags         标签信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void savePost(Contents contents, int categorysKey, List<Metas> tags) {
        int code = 0;
        //新增标签
        List<Metas> addTags = new ArrayList<>();
        //目前文章所含标签关系
        List<Relationships> relationshipsList = new ArrayList<>();
        //目前文章所含新增标签关系
        List<Relationships> addRelationshipsList = new ArrayList<>();
        boolean isNewContent = false;
        try {
            //判断文章是否存在
            if (contents.getCid() == null) {
                isNewContent = true;
                //插入新文章
                comtentsMapper.insertSelective(contents);
                contents.setSlug(contents.getCid().toString());
                //更新slug
                comtentsMapper.updateByPrimaryKeySelective(contents);
            } else {
                //更新文章信息
                comtentsMapper.updateByPrimaryKeySelective(contents);
            }

            //提取新增标签
            for (Metas tag : tags) {
                if (tag.getMid() == null) {
                    addTags.add(tag);
                }
            }
            //给新增加的标签添加mid
            if (addTags.size() != 0) {
                for (Metas tag : addTags) {
                    metasMapper.insertSelective(tag);
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
            if (addRelationshipsList.size() != 0) {
                relationshipsMapper.insertMultiple(addRelationshipsList);
            }
            if (isNewContent) {
                //更新分类信息
                relationshipsMapper.insert(relationships);
            } else {
                //更新分类信息
                relationshipsMapper.updateCategoryByPrimaryKey(relationships);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * savePage  保存页面信息
     *
     * @param contents 页面信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void savePage(Contents contents) {
        int code = 0;
        try {
            //判断文章是否存在
            if (contents.getCid() == null) {
                comtentsMapper.insertSelective(contents);
            } else {
                comtentsMapper.updateByPrimaryKeySelective(contents);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
