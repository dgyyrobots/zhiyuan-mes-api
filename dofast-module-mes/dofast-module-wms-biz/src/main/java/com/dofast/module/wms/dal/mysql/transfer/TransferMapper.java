package com.dofast.module.wms.dal.mysql.transfer;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.wms.dal.dataobject.transfer.TransferDO;
import com.dofast.module.wms.dal.dataobject.transfer.TransferTxBean;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.wms.controller.admin.transfer.vo.*;

/**
 * 转移单 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TransferMapper extends BaseMapperX<TransferDO> {
     List<TransferTxBean> getTxBeans(Long transferId);
    default TransferDO checkUnique(TransferBaseVO baseVO){
        return selectOne(new LambdaQueryWrapperX<TransferDO>().eq(TransferDO::getTransferCode,baseVO.getTransferCode()));
    }
    default PageResult<TransferDO> selectPage(TransferPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TransferDO>()
                .eqIfPresent(TransferDO::getTransferCode, reqVO.getTransferCode())
                .likeIfPresent(TransferDO::getTransferName, reqVO.getTransferName())
                .eqIfPresent(TransferDO::getTransferType, reqVO.getTransferType())
                .eqIfPresent(TransferDO::getDestination, reqVO.getDestination())
                .eqIfPresent(TransferDO::getCarrier, reqVO.getCarrier())
                .eqIfPresent(TransferDO::getBookingNote, reqVO.getBookingNote())
                .eqIfPresent(TransferDO::getReceiver, reqVO.getReceiver())
                .eqIfPresent(TransferDO::getReceiverNick, reqVO.getReceiverNick())
                .eqIfPresent(TransferDO::getFromWarehouseId, reqVO.getFromWarehouseId())
                .eqIfPresent(TransferDO::getFromWarehouseCode, reqVO.getFromWarehouseCode())
                .likeIfPresent(TransferDO::getFromWarehouseName, reqVO.getFromWarehouseName())
                .eqIfPresent(TransferDO::getToWarehouseId, reqVO.getToWarehouseId())
                .eqIfPresent(TransferDO::getToWarehouseCode, reqVO.getToWarehouseCode())
                .likeIfPresent(TransferDO::getToWarehouseName, reqVO.getToWarehouseName())
                .betweenIfPresent(TransferDO::getTransferDate, reqVO.getTransferDate())
                .eqIfPresent(TransferDO::getStatus, reqVO.getStatus())
                .eqIfPresent(TransferDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TransferDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(TransferDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(TransferDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(TransferDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(TransferDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TransferDO::getId));
    }

    default List<TransferDO> selectList(TransferExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<TransferDO>()
                .eqIfPresent(TransferDO::getTransferCode, reqVO.getTransferCode())
                .likeIfPresent(TransferDO::getTransferName, reqVO.getTransferName())
                .eqIfPresent(TransferDO::getTransferType, reqVO.getTransferType())
                .eqIfPresent(TransferDO::getDestination, reqVO.getDestination())
                .eqIfPresent(TransferDO::getCarrier, reqVO.getCarrier())
                .eqIfPresent(TransferDO::getBookingNote, reqVO.getBookingNote())
                .eqIfPresent(TransferDO::getReceiver, reqVO.getReceiver())
                .eqIfPresent(TransferDO::getReceiverNick, reqVO.getReceiverNick())
                .eqIfPresent(TransferDO::getFromWarehouseId, reqVO.getFromWarehouseId())
                .eqIfPresent(TransferDO::getFromWarehouseCode, reqVO.getFromWarehouseCode())
                .likeIfPresent(TransferDO::getFromWarehouseName, reqVO.getFromWarehouseName())
                .eqIfPresent(TransferDO::getToWarehouseId, reqVO.getToWarehouseId())
                .eqIfPresent(TransferDO::getToWarehouseCode, reqVO.getToWarehouseCode())
                .likeIfPresent(TransferDO::getToWarehouseName, reqVO.getToWarehouseName())
                .betweenIfPresent(TransferDO::getTransferDate, reqVO.getTransferDate())
                .eqIfPresent(TransferDO::getStatus, reqVO.getStatus())
                .eqIfPresent(TransferDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TransferDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(TransferDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(TransferDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(TransferDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(TransferDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TransferDO::getId));
    }

}
