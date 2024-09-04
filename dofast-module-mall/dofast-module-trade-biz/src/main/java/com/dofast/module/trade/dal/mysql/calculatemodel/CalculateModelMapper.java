package com.dofast.module.trade.dal.mysql.calculatemodel;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.trade.controller.admin.calculate.calculatemodel.vo.CalculateModelExportReqVO;
import com.dofast.module.trade.controller.admin.calculate.calculatemodel.vo.CalculateModelPageReqVO;
import com.dofast.module.trade.dal.dataobject.calculatemodel.CalculateModelDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 计价模型 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface CalculateModelMapper extends BaseMapperX<CalculateModelDO> {

    default PageResult<CalculateModelDO> selectPage(CalculateModelPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CalculateModelDO>()
                .likeIfPresent(CalculateModelDO::getName, reqVO.getName())
                .eqIfPresent(CalculateModelDO::getDescription, reqVO.getDescription())
                .eqIfPresent(CalculateModelDO::getCategoryId, reqVO.getCategoryId())
                .eqIfPresent(CalculateModelDO::getFormId, reqVO.getFormId())
                .eqIfPresent(CalculateModelDO::getType, reqVO.getType())
                .eqIfPresent(CalculateModelDO::getExpression, reqVO.getExpression())
                .eqIfPresent(CalculateModelDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CalculateModelDO::getSort, reqVO.getSort())
                .betweenIfPresent(CalculateModelDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CalculateModelDO::getId));
    }

    default List<CalculateModelDO> selectList(CalculateModelExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CalculateModelDO>()
                .likeIfPresent(CalculateModelDO::getName, reqVO.getName())
                .eqIfPresent(CalculateModelDO::getDescription, reqVO.getDescription())
                .eqIfPresent(CalculateModelDO::getCategoryId, reqVO.getCategoryId())
                .eqIfPresent(CalculateModelDO::getFormId, reqVO.getFormId())
                .eqIfPresent(CalculateModelDO::getType, reqVO.getType())
                .eqIfPresent(CalculateModelDO::getExpression, reqVO.getExpression())
                .eqIfPresent(CalculateModelDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CalculateModelDO::getSort, reqVO.getSort())
                .betweenIfPresent(CalculateModelDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CalculateModelDO::getId));
    }

}
