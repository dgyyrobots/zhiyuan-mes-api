package com.dofast.module.trade.convert.electronicsheetpackage;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.electronicsheetpackage.vo.ElectronicsheetPackageCreateReqVO;
import com.dofast.module.trade.controller.admin.electronicsheetpackage.vo.ElectronicsheetPackageExcelVO;
import com.dofast.module.trade.controller.admin.electronicsheetpackage.vo.ElectronicsheetPackageRespVO;
import com.dofast.module.trade.controller.admin.electronicsheetpackage.vo.ElectronicsheetPackageUpdateReqVO;
import com.dofast.module.trade.dal.dataobject.electronicsheetpackage.ElectronicsheetPackageDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 电子面单 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface ElectronicsheetPackageConvert {

    ElectronicsheetPackageConvert INSTANCE = Mappers.getMapper(ElectronicsheetPackageConvert.class);

    ElectronicsheetPackageDO convert(ElectronicsheetPackageCreateReqVO bean);

    ElectronicsheetPackageDO convert(ElectronicsheetPackageUpdateReqVO bean);

    ElectronicsheetPackageRespVO convert(ElectronicsheetPackageDO bean);

    List<ElectronicsheetPackageRespVO> convertList(List<ElectronicsheetPackageDO> list);

    PageResult<ElectronicsheetPackageRespVO> convertPage(PageResult<ElectronicsheetPackageDO> page);

    List<ElectronicsheetPackageExcelVO> convertList02(List<ElectronicsheetPackageDO> list);

}
