package com.dofast.module.cmms.dal.mysql.dvmachinery;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.cmms.dal.dataobject.dvmachinery.DvMachineryDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.cmms.controller.admin.dvmachinery.vo.*;

/**
 * 设备台账 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface DvMachineryMapper extends BaseMapperX<DvMachineryDO> {

    default PageResult<DvMachineryDO> selectPage(DvMachineryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DvMachineryDO>()
                .eqIfPresent(DvMachineryDO::getMachineryCode, reqVO.getMachineryCode())
                .likeIfPresent(DvMachineryDO::getMachineryName, reqVO.getMachineryName())
                .eqIfPresent(DvMachineryDO::getMachineryBrand, reqVO.getMachineryBrand())
                .eqIfPresent(DvMachineryDO::getMachinerySpec, reqVO.getMachinerySpec())
                .eqIfPresent(DvMachineryDO::getMachineryTypeId, reqVO.getMachineryTypeId())
                .eqIfPresent(DvMachineryDO::getMachineryTypeCode, reqVO.getMachineryTypeCode())
                .likeIfPresent(DvMachineryDO::getMachineryTypeName, reqVO.getMachineryTypeName())
                .eqIfPresent(DvMachineryDO::getWorkshopId, reqVO.getWorkshopId())
                .eqIfPresent(DvMachineryDO::getWorkshopCode, reqVO.getWorkshopCode())
                .likeIfPresent(DvMachineryDO::getWorkshopName, reqVO.getWorkshopName())
                .eqIfPresent(DvMachineryDO::getStatus, reqVO.getStatus())
                .eqIfPresent(DvMachineryDO::getRemark, reqVO.getRemark())
                .eqIfPresent(DvMachineryDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(DvMachineryDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(DvMachineryDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(DvMachineryDO::getAttr4, reqVO.getAttr4())
                .eqIfPresent(DvMachineryDO::getErpMachineryCode, reqVO.getErpMachineryCode())
                .betweenIfPresent(DvMachineryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DvMachineryDO::getId));
    }

    default List<DvMachineryDO> selectList(DvMachineryExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DvMachineryDO>()
                .eqIfPresent(DvMachineryDO::getMachineryCode, reqVO.getMachineryCode())
                .likeIfPresent(DvMachineryDO::getMachineryName, reqVO.getMachineryName())
                .eqIfPresent(DvMachineryDO::getMachineryBrand, reqVO.getMachineryBrand())
                .eqIfPresent(DvMachineryDO::getMachinerySpec, reqVO.getMachinerySpec())
                .eqIfPresent(DvMachineryDO::getMachineryTypeId, reqVO.getMachineryTypeId())
                .eqIfPresent(DvMachineryDO::getMachineryTypeCode, reqVO.getMachineryTypeCode())
                .likeIfPresent(DvMachineryDO::getMachineryTypeName, reqVO.getMachineryTypeName())
                .eqIfPresent(DvMachineryDO::getWorkshopId, reqVO.getWorkshopId())
                .eqIfPresent(DvMachineryDO::getWorkshopCode, reqVO.getWorkshopCode())
                .likeIfPresent(DvMachineryDO::getWorkshopName, reqVO.getWorkshopName())
                .eqIfPresent(DvMachineryDO::getStatus, reqVO.getStatus())
                .eqIfPresent(DvMachineryDO::getRemark, reqVO.getRemark())
                .eqIfPresent(DvMachineryDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(DvMachineryDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(DvMachineryDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(DvMachineryDO::getAttr4, reqVO.getAttr4())
                .eqIfPresent(DvMachineryDO::getErpMachineryCode, reqVO.getErpMachineryCode())
                .betweenIfPresent(DvMachineryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DvMachineryDO::getId));
    }

}
