package com.dofast.module.tm.dal.mysql.tooltype;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.tm.dal.dataobject.tooltype.ToolTypeDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.tm.controller.admin.tooltype.vo.*;

/**
 * 工装夹具类型 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface ToolTypeMapper extends BaseMapperX<ToolTypeDO> {

    default PageResult<ToolTypeDO> selectPage(ToolTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ToolTypeDO>()
                .eqIfPresent(ToolTypeDO::getToolTypeCode, reqVO.getToolTypeCode())
                .likeIfPresent(ToolTypeDO::getToolTypeName, reqVO.getToolTypeName())
                .eqIfPresent(ToolTypeDO::getCodeFlag, reqVO.getCodeFlag())
                .eqIfPresent(ToolTypeDO::getMaintenType, reqVO.getMaintenType())
                .eqIfPresent(ToolTypeDO::getMaintenPeriod, reqVO.getMaintenPeriod())
                .eqIfPresent(ToolTypeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ToolTypeDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(ToolTypeDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(ToolTypeDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(ToolTypeDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(ToolTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ToolTypeDO::getId));
    }

    default List<ToolTypeDO> selectList(ToolTypeExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ToolTypeDO>()
                .eqIfPresent(ToolTypeDO::getToolTypeCode, reqVO.getToolTypeCode())
                .likeIfPresent(ToolTypeDO::getToolTypeName, reqVO.getToolTypeName())
                .eqIfPresent(ToolTypeDO::getCodeFlag, reqVO.getCodeFlag())
                .eqIfPresent(ToolTypeDO::getMaintenType, reqVO.getMaintenType())
                .eqIfPresent(ToolTypeDO::getMaintenPeriod, reqVO.getMaintenPeriod())
                .eqIfPresent(ToolTypeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ToolTypeDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(ToolTypeDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(ToolTypeDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(ToolTypeDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(ToolTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ToolTypeDO::getId));
    }

    List<ToolTypeSimpleVO> selectSimpleList();

}
