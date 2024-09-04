package com.dofast.module.wms.convert.warehouse;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.api.WarehosueApi.dto.WarehouseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.warehouse.vo.*;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;

/**
 * 仓库 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface WarehouseConvert {

    WarehouseConvert INSTANCE = Mappers.getMapper(WarehouseConvert.class);

    WarehouseDO convert(WarehouseCreateReqVO bean);

    WarehouseDO convert(WarehouseUpdateReqVO bean);

    WarehouseRespVO convert(WarehouseDO bean);

    WarehouseDTO convert01(WarehouseDO bean);

    List<WarehouseRespVO> convertList(List<WarehouseDO> list);

    PageResult<WarehouseRespVO> convertPage(PageResult<WarehouseDO> page);

    List<WarehouseExcelVO> convertList02(List<WarehouseDO> list);

}
