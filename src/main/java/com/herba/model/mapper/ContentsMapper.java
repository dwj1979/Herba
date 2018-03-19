package com.herba.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.herba.model.dto.ContentsInfo;
import com.herba.model.entity.Contents;
import com.herba.model.entity.Relationships;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ContentsMapper  文章Mapper类
 *
 * @author Varshonwood
 * @version 1.0
 * @date 17/11/07
 */

@Component
@Mapper
public interface ContentsMapper {

    /**
     * deleteByPrimaryKey  通过ID删除文章
     *
     * @param cid 文章ID
     * @return
     */
    int deleteByPrimaryKey(Integer cid);


    /**
     * insertSelective  新增文章部分字段
     *
     * @param record 文章实例
     * @return
     */
    int insertSelective(Contents record);

    /**
     * selectPostByPrimaryKey       通过ID查询文章
     *
     * @param cid 文章ID
     * @return
     */
    ContentsInfo selectPostByPrimaryKey(Integer cid);

    /**
     * selectPostByPrimaryKey       通过ID查询文章
     *
     * @param cid 页面ID
     * @return
     */
    ContentsInfo selectPageByPrimaryKey(Integer cid);
    /**
     * selectPost      查询文章信息
     *
     * @return
     */
    List<ContentsInfo> selectPost();
    /**
     * selectPostByMeta      根据分类或者标签查询文章信息
     *
     * @return
     */
    List<ContentsInfo> selectPostByMeta(Relationships relationships);

    /**
     * selectPage       查询页面信息
     *
     * @return
     */
    List<ContentsInfo> selectPage();

    /**
     * updateByPrimaryKeySelective  根据ID更新文章部分字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Contents record);

}


//~ Formatted by Jindent --- http://www.jindent.com
