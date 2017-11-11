package org.herba.model.dto;

import java.util.List;

import org.herba.model.entity.Metas;

import lombok.Data;

/**
 * ContentDetail    每页文章和页面的具体封装，包含全部的标签和分类信息
 *
 * @version        1.0
 * @author         Varshonwood
 * @date           17/11/11
 */
@Data
public class ContentDetail {
    private ContentsInfo contentsInfo;
    private List<Metas>  metas;

    /**
     * ContentDetail
     */
    public ContentDetail() {}

    /**
     * ContentDetail
     *
     * @param contentsInfo
     * @param metas
     */
    public ContentDetail(ContentsInfo contentsInfo, List<Metas> metas) {
        this.contentsInfo = contentsInfo;
        this.metas        = metas;
    }
}
//~ Formatted by Jindent --- http://www.jindent.com
