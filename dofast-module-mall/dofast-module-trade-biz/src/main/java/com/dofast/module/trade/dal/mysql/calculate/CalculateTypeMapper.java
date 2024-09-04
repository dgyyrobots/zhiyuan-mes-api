package com.dofast.module.trade.dal.mysql.calculate;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateTypeExportReqVO;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateTypePageReqVO;
import com.dofast.module.trade.dal.dataobject.calculate.CalculateTypeDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 计价类型 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface CalculateTypeMapper extends BaseMapperX<CalculateTypeDO> {

    default PageResult<CalculateTypeDO> selectPage(CalculateTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CalculateTypeDO>()
                .likeIfPresent(CalculateTypeDO::getName, reqVO.getName())
                .eqIfPresent(CalculateTypeDO::getFormId, reqVO.getFormId())
                .eqIfPresent(CalculateTypeDO::getType, reqVO.getType())
                .eqIfPresent(CalculateTypeDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(CalculateTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CalculateTypeDO::getId));
    }

    default List<CalculateTypeDO> selectList(CalculateTypeExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CalculateTypeDO>()
                .likeIfPresent(CalculateTypeDO::getName, reqVO.getName())
                .eqIfPresent(CalculateTypeDO::getFormId, reqVO.getFormId())
                .eqIfPresent(CalculateTypeDO::getType, reqVO.getType())
                .eqIfPresent(CalculateTypeDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(CalculateTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CalculateTypeDO::getId));
    }

}
