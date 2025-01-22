package com.dofast.module.cmms.convert.dvmachinery;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.cmms.api.dvmachinery.dto.DvMachineryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.cmms.controller.admin.dvmachinery.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvmachinery.DvMachineryDO;

/**
 * 设备台账 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface DvMachineryConvert {

    DvMachineryConvert INSTANCE = Mappers.getMapper(DvMachineryConvert.class);

    DvMachineryDO convert(DvMachineryCreateReqVO bean);

    DvMachineryDO convert(DvMachineryUpdateReqVO bean);

    DvMachineryRespVO convert(DvMachineryDO bean);

    DvMachineryDTO convert01(DvMachineryDO bean);

    DvMachineryUpdateReqVO convert02(DvMachineryDTO bean) ;

    List<DvMachineryRespVO> convertList(List<DvMachineryDO> list);

    PageResult<DvMachineryRespVO> convertPage(PageResult<DvMachineryDO> page);

    List<DvMachineryExcelVO> convertList02(List<DvMachineryDO> list);

}
