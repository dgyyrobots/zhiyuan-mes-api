package com.dofast.module.wms.convert.storagelocation;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.api.StorageLocationApi.dto.StorageLocationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.storagelocation.vo.*;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;

/**
 * 库区 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface StorageLocationConvert {

    StorageLocationConvert INSTANCE = Mappers.getMapper(StorageLocationConvert.class);

    StorageLocationDO convert(StorageLocationCreateReqVO bean);

    StorageLocationDO convert(StorageLocationUpdateReqVO bean);

    StorageLocationRespVO convert(StorageLocationDO bean);
    StorageLocationDTO convert01(StorageLocationDO bean);

    List<StorageLocationRespVO> convertList(List<StorageLocationDO> list);

    PageResult<StorageLocationRespVO> convertPage(PageResult<StorageLocationDO> page);

    List<StorageLocationExcelVO> convertList02(List<StorageLocationDO> list);

}
