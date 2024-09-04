package com.dofast.module.mes.dal.mysql.Autocoderule;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.mes.dal.dataobject.Autocoderule.AutoCodeRuleDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.mes.controller.admin.Autocoderule.vo.*;

/**
 * 编码生成规则 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface AutoCodeRuleMapper extends BaseMapperX<AutoCodeRuleDO> {
    default AutoCodeRuleDO checkRuleCodeUnique(String code){
        return selectOne(new LambdaQueryWrapperX<AutoCodeRuleDO>().eq(AutoCodeRuleDO::getRuleCode,code));
    }
    default AutoCodeRuleDO checkRuleNameUnique(String name){
        return selectOne(new LambdaQueryWrapperX<AutoCodeRuleDO>().eq(AutoCodeRuleDO::getRuleName,name));
    }
    default List<AutoCodeRuleDO> selectList(AutoCodeRuleListVO listVO){
        return selectList(new LambdaQueryWrapperX<AutoCodeRuleDO>()
                .eqIfPresent(AutoCodeRuleDO::getId,listVO.getId())
                .eqIfPresent(AutoCodeRuleDO::getRuleCode, listVO.getRuleCode())
                .likeIfPresent(AutoCodeRuleDO::getRuleName, listVO.getRuleName())
        );
    }

    default PageResult<AutoCodeRuleDO> selectPage(AutoCodeRulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AutoCodeRuleDO>()
                .eqIfPresent(AutoCodeRuleDO::getRuleCode, reqVO.getRuleCode())
                .likeIfPresent(AutoCodeRuleDO::getRuleName, reqVO.getRuleName())
                .eqIfPresent(AutoCodeRuleDO::getRuleDesc, reqVO.getRuleDesc())
                .eqIfPresent(AutoCodeRuleDO::getMaxLength, reqVO.getMaxLength())
                .eqIfPresent(AutoCodeRuleDO::getIsPadded, reqVO.getIsPadded())
                .eqIfPresent(AutoCodeRuleDO::getPaddedChar, reqVO.getPaddedChar())
                .eqIfPresent(AutoCodeRuleDO::getPaddedMethod, reqVO.getPaddedMethod())
                .eqIfPresent(AutoCodeRuleDO::getEnableFlag, reqVO.getEnableFlag())
                .eqIfPresent(AutoCodeRuleDO::getRemark, reqVO.getRemark())
                .eqIfPresent(AutoCodeRuleDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(AutoCodeRuleDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(AutoCodeRuleDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(AutoCodeRuleDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(AutoCodeRuleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AutoCodeRuleDO::getId));
    }

    default List<AutoCodeRuleDO> selectList(AutoCodeRuleExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AutoCodeRuleDO>()
                .eqIfPresent(AutoCodeRuleDO::getRuleCode, reqVO.getRuleCode())
                .likeIfPresent(AutoCodeRuleDO::getRuleName, reqVO.getRuleName())
                .eqIfPresent(AutoCodeRuleDO::getRuleDesc, reqVO.getRuleDesc())
                .eqIfPresent(AutoCodeRuleDO::getMaxLength, reqVO.getMaxLength())
                .eqIfPresent(AutoCodeRuleDO::getIsPadded, reqVO.getIsPadded())
                .eqIfPresent(AutoCodeRuleDO::getPaddedChar, reqVO.getPaddedChar())
                .eqIfPresent(AutoCodeRuleDO::getPaddedMethod, reqVO.getPaddedMethod())
                .eqIfPresent(AutoCodeRuleDO::getEnableFlag, reqVO.getEnableFlag())
                .eqIfPresent(AutoCodeRuleDO::getRemark, reqVO.getRemark())
                .eqIfPresent(AutoCodeRuleDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(AutoCodeRuleDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(AutoCodeRuleDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(AutoCodeRuleDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(AutoCodeRuleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AutoCodeRuleDO::getId));
    }

}
