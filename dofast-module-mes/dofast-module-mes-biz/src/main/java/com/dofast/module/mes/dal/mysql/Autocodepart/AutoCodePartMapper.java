package com.dofast.module.mes.dal.mysql.Autocodepart;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.mes.dal.dataobject.Autocodepart.AutoCodePartDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.mes.controller.admin.Autocodepart.vo.*;

/**
 * 编码生成规则组成 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface AutoCodePartMapper extends BaseMapperX<AutoCodePartDO> {
    AutoCodePartDO checkPartUnique(AutoCodePartBaseVO baseVO);
    default List<AutoCodePartDO> selectList(AutoCodePartListVO listVO){
        return selectList(new LambdaQueryWrapperX<AutoCodePartDO>()
                .eqIfPresent(AutoCodePartDO::getId,listVO.getId())
                .eqIfPresent(AutoCodePartDO::getRuleId,listVO.getRuleId())
                .eqIfPresent(AutoCodePartDO::getPartIndex,listVO.getPartIndex())
                .eqIfPresent(AutoCodePartDO::getPartType,listVO.getPartType())
                .eqIfPresent(AutoCodePartDO::getPartCode,listVO.getPartCode())
                .likeIfPresent(AutoCodePartDO::getPartName,listVO.getPartName())
                .orderByAsc(AutoCodePartDO::getPartIndex)
        );
    }
    default PageResult<AutoCodePartDO> selectPage(AutoCodePartPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AutoCodePartDO>()
                .eqIfPresent(AutoCodePartDO::getRuleId, reqVO.getRuleId())
                .eqIfPresent(AutoCodePartDO::getPartIndex, reqVO.getPartIndex())
                .eqIfPresent(AutoCodePartDO::getPartType, reqVO.getPartType())
                .eqIfPresent(AutoCodePartDO::getPartCode, reqVO.getPartCode())
                .likeIfPresent(AutoCodePartDO::getPartName, reqVO.getPartName())
                .eqIfPresent(AutoCodePartDO::getPartLength, reqVO.getPartLength())
                .eqIfPresent(AutoCodePartDO::getDateFormat, reqVO.getDateFormat())
                .eqIfPresent(AutoCodePartDO::getInputCharacter, reqVO.getInputCharacter())
                .eqIfPresent(AutoCodePartDO::getFixCharacter, reqVO.getFixCharacter())
                .eqIfPresent(AutoCodePartDO::getSeriaStartNo, reqVO.getSeriaStartNo())
                .eqIfPresent(AutoCodePartDO::getSeriaStep, reqVO.getSeriaStep())
                .eqIfPresent(AutoCodePartDO::getSeriaNowNo, reqVO.getSeriaNowNo())
                .eqIfPresent(AutoCodePartDO::getCycleFlag, reqVO.getCycleFlag())
                .eqIfPresent(AutoCodePartDO::getCycleMethod, reqVO.getCycleMethod())
                .eqIfPresent(AutoCodePartDO::getRemark, reqVO.getRemark())
                .eqIfPresent(AutoCodePartDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(AutoCodePartDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(AutoCodePartDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(AutoCodePartDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(AutoCodePartDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AutoCodePartDO::getId));
    }

    default List<AutoCodePartDO> selectList(AutoCodePartExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AutoCodePartDO>()
                .eqIfPresent(AutoCodePartDO::getRuleId, reqVO.getRuleId())
                .eqIfPresent(AutoCodePartDO::getPartIndex, reqVO.getPartIndex())
                .eqIfPresent(AutoCodePartDO::getPartType, reqVO.getPartType())
                .eqIfPresent(AutoCodePartDO::getPartCode, reqVO.getPartCode())
                .likeIfPresent(AutoCodePartDO::getPartName, reqVO.getPartName())
                .eqIfPresent(AutoCodePartDO::getPartLength, reqVO.getPartLength())
                .eqIfPresent(AutoCodePartDO::getDateFormat, reqVO.getDateFormat())
                .eqIfPresent(AutoCodePartDO::getInputCharacter, reqVO.getInputCharacter())
                .eqIfPresent(AutoCodePartDO::getFixCharacter, reqVO.getFixCharacter())
                .eqIfPresent(AutoCodePartDO::getSeriaStartNo, reqVO.getSeriaStartNo())
                .eqIfPresent(AutoCodePartDO::getSeriaStep, reqVO.getSeriaStep())
                .eqIfPresent(AutoCodePartDO::getSeriaNowNo, reqVO.getSeriaNowNo())
                .eqIfPresent(AutoCodePartDO::getCycleFlag, reqVO.getCycleFlag())
                .eqIfPresent(AutoCodePartDO::getCycleMethod, reqVO.getCycleMethod())
                .eqIfPresent(AutoCodePartDO::getRemark, reqVO.getRemark())
                .eqIfPresent(AutoCodePartDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(AutoCodePartDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(AutoCodePartDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(AutoCodePartDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(AutoCodePartDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AutoCodePartDO::getId));
    }

}
