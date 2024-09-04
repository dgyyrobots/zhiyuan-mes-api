package com.dofast.module.wms.convert.package1;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.package1.vo.*;
import com.dofast.module.wms.dal.dataobject.package1.PackageDO;

/**
 * 装箱单 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface PackageConvert {

    PackageConvert INSTANCE = Mappers.getMapper(PackageConvert.class);

    PackageDO convert(PackageCreateReqVO bean);

    PackageDO convert(PackageUpdateReqVO bean);

    PackageRespVO convert(PackageDO bean);
    PackageUpdateReqVO convert01(PackageCreateReqVO bean);

    List<PackageRespVO> convertList(List<PackageDO> list);

    PageResult<PackageRespVO> convertPage(PageResult<PackageDO> page);

    List<PackageExcelVO> convertList02(List<PackageDO> list);

}
