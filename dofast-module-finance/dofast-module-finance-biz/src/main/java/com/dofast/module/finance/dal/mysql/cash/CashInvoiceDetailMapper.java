package com.dofast.module.finance.dal.mysql.cash;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.finance.controller.admin.bpm.cashInvoiceDetail.vo.CashInvoiceDetailBpmPageReqVO;
import com.dofast.module.finance.dal.dataobject.cash.CashInvoiceDetailDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.finance.controller.admin.cash.vo.*;

/**
 * 财务发票明细 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface CashInvoiceDetailMapper extends BaseMapperX<CashInvoiceDetailDO> {

    default PageResult<CashInvoiceDetailDO> selectPage(CashInvoiceDetailPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CashInvoiceDetailDO>()
                .eqIfPresent(CashInvoiceDetailDO::getInvoice, reqVO.getInvoice())
                .likeIfPresent(CashInvoiceDetailDO::getItem, reqVO.getItem())
                .eqIfPresent(CashInvoiceDetailDO::getItemType, reqVO.getItemType())
                .eqIfPresent(CashInvoiceDetailDO::getItemId, reqVO.getItemId())
                .eqIfPresent(CashInvoiceDetailDO::getModel, reqVO.getModel())
                .eqIfPresent(CashInvoiceDetailDO::getUnit, reqVO.getUnit())
                .eqIfPresent(CashInvoiceDetailDO::getAmount, reqVO.getAmount())
                .eqIfPresent(CashInvoiceDetailDO::getPrice, reqVO.getPrice())
                .eqIfPresent(CashInvoiceDetailDO::getMoney, reqVO.getMoney())
                .eqIfPresent(CashInvoiceDetailDO::getTaxRate, reqVO.getTaxRate())
                .eqIfPresent(CashInvoiceDetailDO::getTaxMoney, reqVO.getTaxMoney())
                .betweenIfPresent(CashInvoiceDetailDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CashInvoiceDetailDO::getId));
    }

    default List<CashInvoiceDetailDO> selectList(CashInvoiceDetailExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CashInvoiceDetailDO>()
                .eqIfPresent(CashInvoiceDetailDO::getInvoice, reqVO.getInvoice())
                .likeIfPresent(CashInvoiceDetailDO::getItem, reqVO.getItem())
                .eqIfPresent(CashInvoiceDetailDO::getItemType, reqVO.getItemType())
                .eqIfPresent(CashInvoiceDetailDO::getItemId, reqVO.getItemId())
                .eqIfPresent(CashInvoiceDetailDO::getModel, reqVO.getModel())
                .eqIfPresent(CashInvoiceDetailDO::getUnit, reqVO.getUnit())
                .eqIfPresent(CashInvoiceDetailDO::getAmount, reqVO.getAmount())
                .eqIfPresent(CashInvoiceDetailDO::getPrice, reqVO.getPrice())
                .eqIfPresent(CashInvoiceDetailDO::getMoney, reqVO.getMoney())
                .eqIfPresent(CashInvoiceDetailDO::getTaxRate, reqVO.getTaxRate())
                .eqIfPresent(CashInvoiceDetailDO::getTaxMoney, reqVO.getTaxMoney())
                .betweenIfPresent(CashInvoiceDetailDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CashInvoiceDetailDO::getId));
    }

    default PageResult<CashInvoiceDetailDO> selectPage(String userId, CashInvoiceDetailBpmPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CashInvoiceDetailDO>()
                .eqIfPresent(CashInvoiceDetailDO::getInvoice, reqVO.getInvoice())
                .likeIfPresent(CashInvoiceDetailDO::getItem, reqVO.getItem())
                .eqIfPresent(CashInvoiceDetailDO::getItemType, reqVO.getItemType())
                .eqIfPresent(CashInvoiceDetailDO::getItemId, reqVO.getItemId())
                .eqIfPresent(CashInvoiceDetailDO::getModel, reqVO.getModel())
                .eqIfPresent(CashInvoiceDetailDO::getUnit, reqVO.getUnit())
                .eqIfPresent(CashInvoiceDetailDO::getAmount, reqVO.getAmount())
                .eqIfPresent(CashInvoiceDetailDO::getPrice, reqVO.getPrice())
                .eqIfPresent(CashInvoiceDetailDO::getMoney, reqVO.getMoney())
                .eqIfPresent(CashInvoiceDetailDO::getTaxRate, reqVO.getTaxRate())
                .eqIfPresent(CashInvoiceDetailDO::getTaxMoney, reqVO.getTaxMoney())
                .eqIfPresent(BaseDO::getCreator,userId)
                .betweenIfPresent(CashInvoiceDetailDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CashInvoiceDetailDO::getId));
    }

}
