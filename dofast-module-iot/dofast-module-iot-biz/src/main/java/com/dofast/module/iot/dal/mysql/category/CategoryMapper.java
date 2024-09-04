package com.dofast.module.iot.dal.mysql.category;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.iot.dal.dataobject.category.CategoryDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.iot.controller.admin.category.vo.*;

/**
 * 产品分类 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface CategoryMapper extends BaseMapperX<CategoryDO> {

    default PageResult<CategoryDO> selectPage(CategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CategoryDO>()
                .likeIfPresent(CategoryDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(CategoryDO::getIsSys, reqVO.getIsSys())
                .eqIfPresent(CategoryDO::getParentId, reqVO.getParentId())
                .eqIfPresent(CategoryDO::getOrderNum, reqVO.getOrderNum())
                .eqIfPresent(CategoryDO::getDelFlag, reqVO.getDelFlag())
                .eqIfPresent(CategoryDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CategoryDO::getCategoryId));
    }

    default List<CategoryDO> selectList(CategoryExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CategoryDO>()
                .likeIfPresent(CategoryDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(CategoryDO::getIsSys, reqVO.getIsSys())
                .eqIfPresent(CategoryDO::getParentId, reqVO.getParentId())
                .eqIfPresent(CategoryDO::getOrderNum, reqVO.getOrderNum())
                .eqIfPresent(CategoryDO::getDelFlag, reqVO.getDelFlag())
                .eqIfPresent(CategoryDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CategoryDO::getCategoryId));
    }

}
