package com.dofast.module.trade.dal.mysql.electronicsheetpackage;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.trade.controller.admin.electronicsheetpackage.vo.ElectronicsheetPackageExportReqVO;
import com.dofast.module.trade.controller.admin.electronicsheetpackage.vo.ElectronicsheetPackagePageReqVO;
import com.dofast.module.trade.dal.dataobject.electronicsheetpackage.ElectronicsheetPackageDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 电子面单 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface ElectronicsheetPackageMapper extends BaseMapperX<ElectronicsheetPackageDO> {

    default PageResult<ElectronicsheetPackageDO> selectPage(ElectronicsheetPackagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ElectronicsheetPackageDO>()
                .eqIfPresent(ElectronicsheetPackageDO::getOrderNo, reqVO.getOrderNo())
                .eqIfPresent(ElectronicsheetPackageDO::getWaybillCode, reqVO.getWaybillCode())
                .eqIfPresent(ElectronicsheetPackageDO::getParentWaybillCode, reqVO.getParentWaybillCode())
                .eqIfPresent(ElectronicsheetPackageDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ElectronicsheetPackageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ElectronicsheetPackageDO::getId));
    }

    default List<ElectronicsheetPackageDO> selectList(ElectronicsheetPackageExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ElectronicsheetPackageDO>()
                .eqIfPresent(ElectronicsheetPackageDO::getOrderNo, reqVO.getOrderNo())
                .eqIfPresent(ElectronicsheetPackageDO::getWaybillCode, reqVO.getWaybillCode())
                .eqIfPresent(ElectronicsheetPackageDO::getParentWaybillCode, reqVO.getParentWaybillCode())
                .eqIfPresent(ElectronicsheetPackageDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ElectronicsheetPackageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ElectronicsheetPackageDO::getId));
    }

    default List<ElectronicsheetPackageDO> selectByOrderNo(String orderNo){
        return selectList(new LambdaQueryWrapperX<ElectronicsheetPackageDO>()
                .eq(ElectronicsheetPackageDO::getOrderNo,orderNo)
                .orderByDesc(ElectronicsheetPackageDO::getId));
    }
}
