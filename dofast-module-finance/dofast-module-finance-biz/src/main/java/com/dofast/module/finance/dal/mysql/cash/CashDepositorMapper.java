package com.dofast.module.finance.dal.mysql.cash;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.finance.dal.dataobject.cash.CashDepositorDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.finance.controller.admin.cash.vo.*;

/**
 * 资金账号 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface CashDepositorMapper extends BaseMapperX<CashDepositorDO> {

    default PageResult<CashDepositorDO> selectPage(CashDepositorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CashDepositorDO>()
                .eqIfPresent(CashDepositorDO::getType, reqVO.getType())
                .eqIfPresent(CashDepositorDO::getAbbr, reqVO.getAbbr())
                .eqIfPresent(CashDepositorDO::getTitle, reqVO.getTitle())
                .eqIfPresent(CashDepositorDO::getTags, reqVO.getTags())
                .eqIfPresent(CashDepositorDO::getProvider, reqVO.getProvider())
                .eqIfPresent(CashDepositorDO::getBank, reqVO.getBank())
                .eqIfPresent(CashDepositorDO::getCustomerNo, reqVO.getCustomerNo())
                .eqIfPresent(CashDepositorDO::getAccount, reqVO.getAccount())
                .eqIfPresent(CashDepositorDO::getUnionBankNo, reqVO.getUnionBankNo())
                .eqIfPresent(CashDepositorDO::getClearingBankNo, reqVO.getClearingBankNo())
                .eqIfPresent(CashDepositorDO::getIsPublic, reqVO.getIsPublic())
                .eqIfPresent(CashDepositorDO::getCurrency, reqVO.getCurrency())
                .eqIfPresent(CashDepositorDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(CashDepositorDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CashDepositorDO::getId));
    }

    default List<CashDepositorDO> selectList(CashDepositorExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CashDepositorDO>()
                .eqIfPresent(CashDepositorDO::getType, reqVO.getType())
                .eqIfPresent(CashDepositorDO::getAbbr, reqVO.getAbbr())
                .eqIfPresent(CashDepositorDO::getTitle, reqVO.getTitle())
                .eqIfPresent(CashDepositorDO::getTags, reqVO.getTags())
                .eqIfPresent(CashDepositorDO::getProvider, reqVO.getProvider())
                .eqIfPresent(CashDepositorDO::getBank, reqVO.getBank())
                .eqIfPresent(CashDepositorDO::getCustomerNo, reqVO.getCustomerNo())
                .eqIfPresent(CashDepositorDO::getAccount, reqVO.getAccount())
                .eqIfPresent(CashDepositorDO::getUnionBankNo, reqVO.getUnionBankNo())
                .eqIfPresent(CashDepositorDO::getClearingBankNo, reqVO.getClearingBankNo())
                .eqIfPresent(CashDepositorDO::getIsPublic, reqVO.getIsPublic())
                .eqIfPresent(CashDepositorDO::getCurrency, reqVO.getCurrency())
                .eqIfPresent(CashDepositorDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(CashDepositorDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CashDepositorDO::getId));
    }

}
