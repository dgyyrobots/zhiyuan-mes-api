package com.dofast.module.finance.dal.mysql.cash;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.finance.controller.admin.bpm.cashFund.vo.CashFundBpmPageReqVO;
import com.dofast.module.finance.dal.dataobject.cash.CashFundDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.finance.controller.admin.cash.vo.*;

/**
 * 财务退款 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface CashFundMapper extends BaseMapperX<CashFundDO> {

    default PageResult<CashFundDO> selectPage(CashFundPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CashFundDO>()
                .eqIfPresent(CashFundDO::getType, reqVO.getType())
                .eqIfPresent(CashFundDO::getOrigin, reqVO.getOrigin())
                .eqIfPresent(CashFundDO::getParent, reqVO.getParent())
                .eqIfPresent(CashFundDO::getTrader, reqVO.getTrader())
                .eqIfPresent(CashFundDO::getContract, reqVO.getContract())
                .eqIfPresent(CashFundDO::getOrder, reqVO.getOrder())
                .eqIfPresent(CashFundDO::getBatch, reqVO.getBatch())
                .eqIfPresent(CashFundDO::getDeserved, reqVO.getDeserved())
                .eqIfPresent(CashFundDO::getActual, reqVO.getActual())
                .eqIfPresent(CashFundDO::getBalance, reqVO.getBalance())
                .eqIfPresent(CashFundDO::getCustom, reqVO.getCustom())
                .eqIfPresent(CashFundDO::getDescription, reqVO.getDesc())
                .betweenIfPresent(CashFundDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CashFundDO::getId));
    }

    default List<CashFundDO> selectList(CashFundExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CashFundDO>()
                .eqIfPresent(CashFundDO::getType, reqVO.getType())
                .eqIfPresent(CashFundDO::getOrigin, reqVO.getOrigin())
                .eqIfPresent(CashFundDO::getParent, reqVO.getParent())
                .eqIfPresent(CashFundDO::getTrader, reqVO.getTrader())
                .eqIfPresent(CashFundDO::getContract, reqVO.getContract())
                .eqIfPresent(CashFundDO::getOrder, reqVO.getOrder())
                .eqIfPresent(CashFundDO::getBatch, reqVO.getBatch())
                .eqIfPresent(CashFundDO::getDeserved, reqVO.getDeserved())
                .eqIfPresent(CashFundDO::getActual, reqVO.getActual())
                .eqIfPresent(CashFundDO::getBalance, reqVO.getBalance())
                .eqIfPresent(CashFundDO::getCustom, reqVO.getCustom())
                .eqIfPresent(CashFundDO::getDescription, reqVO.getDesc())
                .betweenIfPresent(CashFundDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CashFundDO::getId));
    }

    default PageResult<CashFundDO> selectPage(String userId, CashFundBpmPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CashFundDO>()
                .eqIfPresent(CashFundDO::getType, reqVO.getType())
                .eqIfPresent(CashFundDO::getOrigin, reqVO.getOrigin())
                .eqIfPresent(CashFundDO::getParent, reqVO.getParent())
                .eqIfPresent(CashFundDO::getTrader, reqVO.getTrader())
                .eqIfPresent(CashFundDO::getContract, reqVO.getContract())
                .eqIfPresent(CashFundDO::getOrder, reqVO.getOrder())
                .eqIfPresent(CashFundDO::getBatch, reqVO.getBatch())
                .eqIfPresent(CashFundDO::getDeserved, reqVO.getDeserved())
                .eqIfPresent(CashFundDO::getActual, reqVO.getActual())
                .eqIfPresent(CashFundDO::getBalance, reqVO.getBalance())
                .eqIfPresent(CashFundDO::getCustom, reqVO.getCustom())
                .eqIfPresent(CashFundDO::getDescription, reqVO.getDesc())
                .eqIfPresent(BaseDO::getCreator,userId)
                .betweenIfPresent(CashFundDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CashFundDO::getId));
    }


}
