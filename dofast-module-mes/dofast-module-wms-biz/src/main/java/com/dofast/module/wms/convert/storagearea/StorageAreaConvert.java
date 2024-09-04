package com.dofast.module.wms.convert.storagearea;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.api.StorageAreaApi.dto.StorageAreaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.storagearea.vo.*;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;

/**
 * 库位 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface StorageAreaConvert {

    StorageAreaConvert INSTANCE = Mappers.getMapper(StorageAreaConvert.class);

    StorageAreaDO convert(StorageAreaCreateReqVO bean);

    StorageAreaDO convert(StorageAreaUpdateReqVO bean);

    StorageAreaRespVO convert(StorageAreaDO bean);
    StorageAreaDTO convert01(StorageAreaDO bean);

    List<StorageAreaRespVO> convertList(List<StorageAreaDO> list);

    PageResult<StorageAreaRespVO> convertPage(PageResult<StorageAreaDO> page);

    List<StorageAreaExcelVO> convertList02(List<StorageAreaDO> list);

}
