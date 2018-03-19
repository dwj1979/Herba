package com.herba.model.dto;

import java.util.List;

import com.herba.model.entity.Contents;
import com.herba.model.entity.Metas;

import lombok.Data;

/**
 * ContentSave 后台文章详情保存所使用的类
 *
 * @version        1.0
 * @author         Varshonwood
 * @date           17/11/15
 */
@Data
public class ContentSave {

    /** 文章详情 */
    Contents contents;

    /** 文章分类id */
    int categorysKey;

    /** 文章标签 */
    List<Metas> tags;
}
