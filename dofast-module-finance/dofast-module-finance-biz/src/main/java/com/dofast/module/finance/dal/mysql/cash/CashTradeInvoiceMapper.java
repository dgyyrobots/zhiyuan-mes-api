package com.dofast.module.finance.dal.mysql.cash;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.finance.dal.dataobject.cash.CashTradeInvoiceDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.finance.controller.admin.cash.vo.*;

/**
 * 业务发票关联 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface CashTradeInvoiceMapper extends BaseMapperX<CashTradeInvoiceDO> {

    default PageResult<CashTradeInvoiceDO> selectPage(CashTradeInvoicePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CashTradeInvoiceDO>()
                .eqIfPresent(CashTradeInvoiceDO::getTrade, reqVO.getTrade())
                .eqIfPresent(CashTradeInvoiceDO::getInvoice, reqVO.getInvoice())
                .betweenIfPresent(CashTradeInvoiceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CashTradeInvoiceDO::getCreateTime));
    }

    default List<CashTradeInvoiceDO> selectList(CashTradeInvoiceExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CashTradeInvoiceDO>()
                .eqIfPresent(CashTradeInvoiceDO::getTrade, reqVO.getTrade())
                .eqIfPresent(CashTradeInvoiceDO::getInvoice, reqVO.getInvoice())
                .betweenIfPresent(CashTradeInvoiceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CashTradeInvoiceDO::getCreateTime));
    }

}
