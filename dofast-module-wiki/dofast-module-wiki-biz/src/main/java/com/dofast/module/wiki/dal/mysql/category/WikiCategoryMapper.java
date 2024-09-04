package com.dofast.module.wiki.dal.mysql.category;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.wiki.dal.dataobject.category.WikiCategoryDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.wiki.controller.admin.category.vo.*;

/**
 * 首页的分类 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface WikiCategoryMapper extends BaseMapperX<WikiCategoryDO> {

    default PageResult<WikiCategoryDO> selectPage(WikiCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WikiCategoryDO>()
                .eqIfPresent(WikiCategoryDO::getPid, reqVO.getPid())
                .eqIfPresent(WikiCategoryDO::getCategoryLevel, reqVO.getCategoryLevel())
                .likeIfPresent(WikiCategoryDO::getName, reqVO.getName())
                .betweenIfPresent(WikiCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WikiCategoryDO::getId));
    }

    default List<WikiCategoryDO> selectList(WikiCategoryExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<WikiCategoryDO>()
                .eqIfPresent(WikiCategoryDO::getPid, reqVO.getPid())
                .eqIfPresent(WikiCategoryDO::getCategoryLevel, reqVO.getCategoryLevel())
                .likeIfPresent(WikiCategoryDO::getName, reqVO.getName())
                .betweenIfPresent(WikiCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WikiCategoryDO::getId));
    }

}
