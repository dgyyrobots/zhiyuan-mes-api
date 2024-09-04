package com.dofast.module.channel.dal.mysql.salesorder;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.channel.controller.admin.salesorder.vo.SalesOrderExportReqVO;
import com.dofast.module.channel.controller.admin.salesorder.vo.SalesOrderPageReqVO;
import com.dofast.module.channel.dal.dataobject.salesorder.SalesOrderDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 销售订单 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface SalesOrderMapper extends BaseMapperX<SalesOrderDO> {

    default PageResult<SalesOrderDO> selectPage(SalesOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SalesOrderDO>()
                .eqIfPresent(SalesOrderDO::getSaleNo, reqVO.getSaleNo())
                .eqIfPresent(SalesOrderDO::getTitle, reqVO.getTitle())
                .eqIfPresent(SalesOrderDO::getSettlementMethod, reqVO.getSettlementMethod())
                .eqIfPresent(SalesOrderDO::getPrice, reqVO.getPrice())
                .eqIfPresent(SalesOrderDO::getSaler, reqVO.getSaler())
                .betweenIfPresent(SalesOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SalesOrderDO::getId));
    }

    default List<SalesOrderDO> selectList(SalesOrderExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<SalesOrderDO>()
                .eqIfPresent(SalesOrderDO::getSaleNo, reqVO.getSaleNo())
                .eqIfPresent(SalesOrderDO::getTitle, reqVO.getTitle())
                .eqIfPresent(SalesOrderDO::getSettlementMethod, reqVO.getSettlementMethod())
                .eqIfPresent(SalesOrderDO::getPrice, reqVO.getPrice())
                .eqIfPresent(SalesOrderDO::getSaler, reqVO.getSaler())
                .betweenIfPresent(SalesOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SalesOrderDO::getId));
    }
}
