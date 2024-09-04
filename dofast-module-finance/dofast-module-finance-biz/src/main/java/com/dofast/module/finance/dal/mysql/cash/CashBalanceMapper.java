package com.dofast.module.finance.dal.mysql.cash;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.finance.dal.dataobject.cash.CashBalanceDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.finance.controller.admin.cash.vo.*;

/**
 * 现金余额 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface CashBalanceMapper extends BaseMapperX<CashBalanceDO> {

    default PageResult<CashBalanceDO> selectPage(CashBalancePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CashBalanceDO>()
                .eqIfPresent(CashBalanceDO::getDepositor, reqVO.getDepositor())
                .betweenIfPresent(CashBalanceDO::getDate, reqVO.getDate())
                .betweenIfPresent(CashBalanceDO::getMoney, reqVO.getMoney())
                .betweenIfPresent(CashBalanceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CashBalanceDO::getId));
    }

    default List<CashBalanceDO> selectList(CashBalanceExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CashBalanceDO>()
                .eqIfPresent(CashBalanceDO::getDepositor, reqVO.getDepositor())
                .betweenIfPresent(CashBalanceDO::getDate, reqVO.getDate())
                .betweenIfPresent(CashBalanceDO::getMoney, reqVO.getMoney())
                .betweenIfPresent(CashBalanceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CashBalanceDO::getId));
    }

}
