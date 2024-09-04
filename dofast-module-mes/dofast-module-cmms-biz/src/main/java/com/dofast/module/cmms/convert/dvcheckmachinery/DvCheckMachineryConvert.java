package com.dofast.module.cmms.convert.dvcheckmachinery;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.cmms.controller.admin.dvcheckmachinery.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvcheckmachinery.DvCheckMachineryDO;

/**
 * 点检设备 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface DvCheckMachineryConvert {

    DvCheckMachineryConvert INSTANCE = Mappers.getMapper(DvCheckMachineryConvert.class);

    DvCheckMachineryDO convert(DvCheckMachineryCreateReqVO bean);

    DvCheckMachineryDO convert(DvCheckMachineryUpdateReqVO bean);

    DvCheckMachineryRespVO convert(DvCheckMachineryDO bean);

    List<DvCheckMachineryRespVO> convertList(List<DvCheckMachineryDO> list);

    PageResult<DvCheckMachineryRespVO> convertPage(PageResult<DvCheckMachineryDO> page);

    List<DvCheckMachineryExcelVO> convertList02(List<DvCheckMachineryDO> list);

}
