package com.dofast.module.tm.dal.mysql.tool;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.tm.dal.dataobject.tool.ToolDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.tm.controller.admin.tool.vo.*;

/**
 * 工装夹具清单 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface ToolMapper extends BaseMapperX<ToolDO> {

    default PageResult<ToolDO> selectPage(ToolPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ToolDO>()
                .eqIfPresent(ToolDO::getToolCode, reqVO.getToolCode())
                .likeIfPresent(ToolDO::getToolName, reqVO.getToolName())
                .eqIfPresent(ToolDO::getBrand, reqVO.getBrand())
                .eqIfPresent(ToolDO::getSpec, reqVO.getSpec())
                .eqIfPresent(ToolDO::getToolTypeId, reqVO.getToolTypeId())
                .eqIfPresent(ToolDO::getToolTypeCode, reqVO.getToolTypeCode())
                .likeIfPresent(ToolDO::getToolTypeName, reqVO.getToolTypeName())
                .eqIfPresent(ToolDO::getCodeFlag, reqVO.getCodeFlag())
                .eqIfPresent(ToolDO::getQuantity, reqVO.getQuantity())
                .eqIfPresent(ToolDO::getQuantityAvail, reqVO.getQuantityAvail())
                .eqIfPresent(ToolDO::getMaintenType, reqVO.getMaintenType())
                .eqIfPresent(ToolDO::getNextMaintenPeriod, reqVO.getNextMaintenPeriod())
                .betweenIfPresent(ToolDO::getNextMaintenDate, reqVO.getNextMaintenDate())
                .eqIfPresent(ToolDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ToolDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ToolDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(ToolDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(ToolDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(ToolDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(ToolDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ToolDO::getId));
    }

    default List<ToolDO> selectList(ToolExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ToolDO>()
                .eqIfPresent(ToolDO::getToolCode, reqVO.getToolCode())
                .likeIfPresent(ToolDO::getToolName, reqVO.getToolName())
                .eqIfPresent(ToolDO::getBrand, reqVO.getBrand())
                .eqIfPresent(ToolDO::getSpec, reqVO.getSpec())
                .eqIfPresent(ToolDO::getToolTypeId, reqVO.getToolTypeId())
                .eqIfPresent(ToolDO::getToolTypeCode, reqVO.getToolTypeCode())
                .likeIfPresent(ToolDO::getToolTypeName, reqVO.getToolTypeName())
                .eqIfPresent(ToolDO::getCodeFlag, reqVO.getCodeFlag())
                .eqIfPresent(ToolDO::getQuantity, reqVO.getQuantity())
                .eqIfPresent(ToolDO::getQuantityAvail, reqVO.getQuantityAvail())
                .eqIfPresent(ToolDO::getMaintenType, reqVO.getMaintenType())
                .eqIfPresent(ToolDO::getNextMaintenPeriod, reqVO.getNextMaintenPeriod())
                .betweenIfPresent(ToolDO::getNextMaintenDate, reqVO.getNextMaintenDate())
                .eqIfPresent(ToolDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ToolDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ToolDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(ToolDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(ToolDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(ToolDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(ToolDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ToolDO::getId));
    }

}
