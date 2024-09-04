package com.dofast.module.wms.convert.packageline;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.packageline.vo.*;
import com.dofast.module.wms.dal.dataobject.packageline.PackageLineDO;

/**
 * 装箱明细 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface PackageLineConvert {

    PackageLineConvert INSTANCE = Mappers.getMapper(PackageLineConvert.class);

    PackageLineDO convert(PackageLineCreateReqVO bean);

    PackageLineDO convert(PackageLineUpdateReqVO bean);

    PackageLineRespVO convert(PackageLineDO bean);

    List<PackageLineRespVO> convertList(List<PackageLineDO> list);

    PageResult<PackageLineRespVO> convertPage(PageResult<PackageLineDO> page);

    List<PackageLineExcelVO> convertList02(List<PackageLineDO> list);

}
