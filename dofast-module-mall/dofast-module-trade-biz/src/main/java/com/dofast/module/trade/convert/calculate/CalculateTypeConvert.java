package com.dofast.module.trade.convert.calculate;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateTypeCreateReqVO;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateTypeExcelVO;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateTypeRespVO;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateTypeUpdateReqVO;
import com.dofast.module.trade.dal.dataobject.calculate.CalculateTypeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 计价类型 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface CalculateTypeConvert {

    CalculateTypeConvert INSTANCE = Mappers.getMapper(CalculateTypeConvert.class);

    CalculateTypeDO convert(CalculateTypeCreateReqVO bean);

    CalculateTypeDO convert(CalculateTypeUpdateReqVO bean);

    CalculateTypeRespVO convert(CalculateTypeDO bean);

    List<CalculateTypeRespVO> convertList(List<CalculateTypeDO> list);

    PageResult<CalculateTypeRespVO> convertPage(PageResult<CalculateTypeDO> page);

    List<CalculateTypeExcelVO> convertList02(List<CalculateTypeDO> list);

}
