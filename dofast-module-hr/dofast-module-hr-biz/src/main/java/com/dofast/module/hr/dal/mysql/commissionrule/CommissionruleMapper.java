package com.dofast.module.hr.dal.mysql.commissionrule;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.hr.dal.dataobject.commissionrule.CommissionruleDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.hr.controller.admin.commissionrule.vo.*;

/**
 * 提成规则 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface CommissionruleMapper extends BaseMapperX<CommissionruleDO> {

    default PageResult<CommissionruleDO> selectPage(CommissionrulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CommissionruleDO>()
                .eqIfPresent(CommissionruleDO::getMonth, reqVO.getMonth())
                .eqIfPresent(CommissionruleDO::getAccount, reqVO.getAccount())
                .eqIfPresent(CommissionruleDO::getSale, reqVO.getSale())
                .eqIfPresent(CommissionruleDO::getLine, reqVO.getLine())
                .betweenIfPresent(CommissionruleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CommissionruleDO::getId));
    }

    default List<CommissionruleDO> selectList(CommissionruleExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CommissionruleDO>()
                .eqIfPresent(CommissionruleDO::getMonth, reqVO.getMonth())
                .eqIfPresent(CommissionruleDO::getAccount, reqVO.getAccount())
                .eqIfPresent(CommissionruleDO::getSale, reqVO.getSale())
                .eqIfPresent(CommissionruleDO::getLine, reqVO.getLine())
                .betweenIfPresent(CommissionruleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CommissionruleDO::getId));
    }

}
