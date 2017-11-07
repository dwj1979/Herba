package org.herba.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.herba.model.entity.Contents;
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
     * insert   新增文章全部字段
     *
     * @param record 文章实例
     * @return
     */
    int insert(Contents record);

    /**
     * insertSelective  新增文章部分字段
     *
     * @param record 文章实例
     * @return
     */
    int insertSelective(Contents record);

    /**
     * selectByPrimaryKey       通过ID查询文章
     *
     * @param cid 文章ID
     * @return
     */
    Contents selectByPrimaryKey(Integer cid);

    /**
     * selectByPage       根据分页查询文章
     *
     * @param type 文章类型
     * @return
     */
    List<Contents> selectByPage(String type);

    /**
     * updateByPrimaryKey   根据ID更新除内容以外的其他字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Contents record);

    /**
     * updateByPrimaryKeySelective  根据ID更新文章部分字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Contents record);

    /**
     * updateByPrimaryKeyWithBLOBs  根据ID更新文章全部字段
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(Contents record);
}


//~ Formatted by Jindent --- http://www.jindent.com
