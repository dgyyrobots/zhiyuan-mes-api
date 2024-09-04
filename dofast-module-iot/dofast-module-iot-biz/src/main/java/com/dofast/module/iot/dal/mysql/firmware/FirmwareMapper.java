package com.dofast.module.iot.dal.mysql.firmware;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.iot.dal.dataobject.firmware.FirmwareDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.iot.controller.admin.firmware.vo.*;

/**
 * 产品固件 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface FirmwareMapper extends BaseMapperX<FirmwareDO> {

    default PageResult<FirmwareDO> selectPage(FirmwarePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FirmwareDO>()
                .likeIfPresent(FirmwareDO::getFirmwareName, reqVO.getFirmwareName())
                .eqIfPresent(FirmwareDO::getProductId, reqVO.getProductId())
                .likeIfPresent(FirmwareDO::getProductName, reqVO.getProductName())
                .eqIfPresent(FirmwareDO::getIsSys, reqVO.getIsSys())
                .eqIfPresent(FirmwareDO::getIsLatest, reqVO.getIsLatest())
                .eqIfPresent(FirmwareDO::getVersion, reqVO.getVersion())
                .eqIfPresent(FirmwareDO::getFilePath, reqVO.getFilePath())
                .eqIfPresent(FirmwareDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(FirmwareDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FirmwareDO::getId));
    }

    default List<FirmwareDO> selectList(FirmwareExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<FirmwareDO>()
                .likeIfPresent(FirmwareDO::getFirmwareName, reqVO.getFirmwareName())
                .eqIfPresent(FirmwareDO::getProductId, reqVO.getProductId())
                .likeIfPresent(FirmwareDO::getProductName, reqVO.getProductName())
                .eqIfPresent(FirmwareDO::getIsSys, reqVO.getIsSys())
                .eqIfPresent(FirmwareDO::getIsLatest, reqVO.getIsLatest())
                .eqIfPresent(FirmwareDO::getVersion, reqVO.getVersion())
                .eqIfPresent(FirmwareDO::getFilePath, reqVO.getFilePath())
                .eqIfPresent(FirmwareDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(FirmwareDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FirmwareDO::getId));
    }

}
