package com.dofast.module.wms.dal.mysql.package1;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.wms.dal.dataobject.package1.PackageDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.wms.controller.admin.package1.vo.*;

/**
 * 装箱单 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PackageMapper extends BaseMapperX<PackageDO> {

    default PackageDO selectWmPackageByPackageId(Long packageId){
        return selectOne(new LambdaQueryWrapperX<PackageDO>().eq(PackageDO::getParentId,packageId));
    }
    default PackageDO checkPackgeCodeUnique(PackageBaseVO baseVO){
        return selectOne(new LambdaQueryWrapperX<PackageDO>().eq(PackageDO::getPackageCode,baseVO.getPackageCode()));
    }
    default PageResult<PackageDO> selectPage(PackagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PackageDO>()
                .eqIfPresent(PackageDO::getParentId, reqVO.getParentId())
                .eqIfPresent(PackageDO::getAncestors, reqVO.getAncestors())
                .eqIfPresent(PackageDO::getPackageCode, reqVO.getPackageCode())
                .eqIfPresent(PackageDO::getBarcodeId, reqVO.getBarcodeId())
                .eqIfPresent(PackageDO::getBarcodeContent, reqVO.getBarcodeContent())
                .eqIfPresent(PackageDO::getBarcodeUrl, reqVO.getBarcodeUrl())
                .betweenIfPresent(PackageDO::getPackageDate, reqVO.getPackageDate())
                .eqIfPresent(PackageDO::getSoCode, reqVO.getSoCode())
                .eqIfPresent(PackageDO::getInvoiceCode, reqVO.getInvoiceCode())
                .eqIfPresent(PackageDO::getClientId, reqVO.getClientId())
                .eqIfPresent(PackageDO::getClientCode, reqVO.getClientCode())
                .likeIfPresent(PackageDO::getClientName, reqVO.getClientName())
                .eqIfPresent(PackageDO::getClientNick, reqVO.getClientNick())
                .eqIfPresent(PackageDO::getPackageLength, reqVO.getPackageLength())
                .eqIfPresent(PackageDO::getPackageWidth, reqVO.getPackageWidth())
                .eqIfPresent(PackageDO::getPackageHeight, reqVO.getPackageHeight())
                .eqIfPresent(PackageDO::getSizeUnit, reqVO.getSizeUnit())
                .eqIfPresent(PackageDO::getNetWeight, reqVO.getNetWeight())
                .eqIfPresent(PackageDO::getCrossWeight, reqVO.getCrossWeight())
                .eqIfPresent(PackageDO::getWeightUnit, reqVO.getWeightUnit())
                .eqIfPresent(PackageDO::getInspector, reqVO.getInspector())
                .likeIfPresent(PackageDO::getInspectorName, reqVO.getInspectorName())
                .eqIfPresent(PackageDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PackageDO::getEnableFlag, reqVO.getEnableFlag())
                .eqIfPresent(PackageDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PackageDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(PackageDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(PackageDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(PackageDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(PackageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PackageDO::getId));
    }

    default List<PackageDO> selectList(PackageExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<PackageDO>()
                .eqIfPresent(PackageDO::getParentId, reqVO.getParentId())
                .eqIfPresent(PackageDO::getAncestors, reqVO.getAncestors())
                .eqIfPresent(PackageDO::getPackageCode, reqVO.getPackageCode())
                .eqIfPresent(PackageDO::getBarcodeId, reqVO.getBarcodeId())
                .eqIfPresent(PackageDO::getBarcodeContent, reqVO.getBarcodeContent())
                .eqIfPresent(PackageDO::getBarcodeUrl, reqVO.getBarcodeUrl())
                .betweenIfPresent(PackageDO::getPackageDate, reqVO.getPackageDate())
                .eqIfPresent(PackageDO::getSoCode, reqVO.getSoCode())
                .eqIfPresent(PackageDO::getInvoiceCode, reqVO.getInvoiceCode())
                .eqIfPresent(PackageDO::getClientId, reqVO.getClientId())
                .eqIfPresent(PackageDO::getClientCode, reqVO.getClientCode())
                .likeIfPresent(PackageDO::getClientName, reqVO.getClientName())
                .eqIfPresent(PackageDO::getClientNick, reqVO.getClientNick())
                .eqIfPresent(PackageDO::getPackageLength, reqVO.getPackageLength())
                .eqIfPresent(PackageDO::getPackageWidth, reqVO.getPackageWidth())
                .eqIfPresent(PackageDO::getPackageHeight, reqVO.getPackageHeight())
                .eqIfPresent(PackageDO::getSizeUnit, reqVO.getSizeUnit())
                .eqIfPresent(PackageDO::getNetWeight, reqVO.getNetWeight())
                .eqIfPresent(PackageDO::getCrossWeight, reqVO.getCrossWeight())
                .eqIfPresent(PackageDO::getWeightUnit, reqVO.getWeightUnit())
                .eqIfPresent(PackageDO::getInspector, reqVO.getInspector())
                .likeIfPresent(PackageDO::getInspectorName, reqVO.getInspectorName())
                .eqIfPresent(PackageDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PackageDO::getEnableFlag, reqVO.getEnableFlag())
                .eqIfPresent(PackageDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PackageDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(PackageDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(PackageDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(PackageDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(PackageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PackageDO::getId));
    }

}
