package com.dofast.module.cal.dal.mysql.shift;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.cal.dal.dataobject.shift.ShiftDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.cal.controller.admin.shift.vo.*;

/**
 * 计划班次 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface ShiftMapper extends BaseMapperX<ShiftDO> {

    default PageResult<ShiftDO> selectPage(ShiftPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ShiftDO>()
                .eqIfPresent(ShiftDO::getPlanId, reqVO.getPlanId())
                .eqIfPresent(ShiftDO::getOrderNum, reqVO.getOrderNum())
                .likeIfPresent(ShiftDO::getShiftName, reqVO.getShiftName())
                .betweenIfPresent(ShiftDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(ShiftDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(ShiftDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ShiftDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(ShiftDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(ShiftDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(ShiftDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(ShiftDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ShiftDO::getId));
    }

    default List<ShiftDO> selectList(ShiftExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ShiftDO>()
                .eqIfPresent(ShiftDO::getPlanId, reqVO.getPlanId())
                .eqIfPresent(ShiftDO::getOrderNum, reqVO.getOrderNum())
                .likeIfPresent(ShiftDO::getShiftName, reqVO.getShiftName())
                .betweenIfPresent(ShiftDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(ShiftDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(ShiftDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ShiftDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(ShiftDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(ShiftDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(ShiftDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(ShiftDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ShiftDO::getId));
    }

}
