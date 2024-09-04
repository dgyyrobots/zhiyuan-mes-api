package com.dofast.module.trade.dal.mysql.calculatecategory;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.trade.controller.admin.calculate.calculatecategory.vo.CalculateCategoryExportReqVO;
import com.dofast.module.trade.controller.admin.calculate.calculatecategory.vo.CalculateCategoryPageReqVO;
import com.dofast.module.trade.dal.dataobject.calculatecategory.CalculateCategoryDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 计价分类 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface CalculateCategoryMapper extends BaseMapperX<CalculateCategoryDO> {

    default PageResult<CalculateCategoryDO> selectPage(CalculateCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CalculateCategoryDO>()
                .likeIfPresent(CalculateCategoryDO::getName, reqVO.getName())
                .eqIfPresent(CalculateCategoryDO::getDescription, reqVO.getDescription())
                .eqIfPresent(CalculateCategoryDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CalculateCategoryDO::getSort, reqVO.getSort())
                .betweenIfPresent(CalculateCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CalculateCategoryDO::getId));
    }

    default List<CalculateCategoryDO> selectList(CalculateCategoryExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CalculateCategoryDO>()
                .likeIfPresent(CalculateCategoryDO::getName, reqVO.getName())
                .eqIfPresent(CalculateCategoryDO::getDescription, reqVO.getDescription())
                .eqIfPresent(CalculateCategoryDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CalculateCategoryDO::getSort, reqVO.getSort())
                .betweenIfPresent(CalculateCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CalculateCategoryDO::getId));
    }

}
