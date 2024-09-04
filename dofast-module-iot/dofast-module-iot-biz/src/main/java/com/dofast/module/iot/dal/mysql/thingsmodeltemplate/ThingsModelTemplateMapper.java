package com.dofast.module.iot.dal.mysql.thingsmodeltemplate;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.iot.dal.dataobject.thingsmodeltemplate.ThingsModelTemplateDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.iot.controller.admin.thingsmodeltemplate.vo.*;

/**
 * 物模型模板 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface ThingsModelTemplateMapper extends BaseMapperX<ThingsModelTemplateDO> {

    default PageResult<ThingsModelTemplateDO> selectPage(ThingsModelTemplatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ThingsModelTemplateDO>()
                .likeIfPresent(ThingsModelTemplateDO::getTemplateName, reqVO.getTemplateName())
                .eqIfPresent(ThingsModelTemplateDO::getIdentifier, reqVO.getIdentifier())
                .eqIfPresent(ThingsModelTemplateDO::getType, reqVO.getType())
                .eqIfPresent(ThingsModelTemplateDO::getDatatype, reqVO.getDatatype())
                .eqIfPresent(ThingsModelTemplateDO::getSpecs, reqVO.getSpecs())
                .eqIfPresent(ThingsModelTemplateDO::getIsSys, reqVO.getIsSys())
                .eqIfPresent(ThingsModelTemplateDO::getIsTop, reqVO.getIsTop())
                .eqIfPresent(ThingsModelTemplateDO::getIsMonitor, reqVO.getIsMonitor())
                .eqIfPresent(ThingsModelTemplateDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(ThingsModelTemplateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ThingsModelTemplateDO::getId));
    }

    default List<ThingsModelTemplateDO> selectList(ThingsModelTemplateExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ThingsModelTemplateDO>()
                .likeIfPresent(ThingsModelTemplateDO::getTemplateName, reqVO.getTemplateName())
                .eqIfPresent(ThingsModelTemplateDO::getIdentifier, reqVO.getIdentifier())
                .eqIfPresent(ThingsModelTemplateDO::getType, reqVO.getType())
                .eqIfPresent(ThingsModelTemplateDO::getDatatype, reqVO.getDatatype())
                .eqIfPresent(ThingsModelTemplateDO::getSpecs, reqVO.getSpecs())
                .eqIfPresent(ThingsModelTemplateDO::getIsSys, reqVO.getIsSys())
                .eqIfPresent(ThingsModelTemplateDO::getIsTop, reqVO.getIsTop())
                .eqIfPresent(ThingsModelTemplateDO::getIsMonitor, reqVO.getIsMonitor())
                .eqIfPresent(ThingsModelTemplateDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(ThingsModelTemplateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ThingsModelTemplateDO::getId));
    }

}
