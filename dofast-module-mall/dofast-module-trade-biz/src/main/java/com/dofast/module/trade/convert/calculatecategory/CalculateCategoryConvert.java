package com.dofast.module.trade.convert.calculatecategory;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.calculate.calculatecategory.vo.CalculateCategoryCreateReqVO;
import com.dofast.module.trade.controller.admin.calculate.calculatecategory.vo.CalculateCategoryExcelVO;
import com.dofast.module.trade.controller.admin.calculate.calculatecategory.vo.CalculateCategoryRespVO;
import com.dofast.module.trade.controller.admin.calculate.calculatecategory.vo.CalculateCategoryUpdateReqVO;
import com.dofast.module.trade.dal.dataobject.calculatecategory.CalculateCategoryDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 计价分类 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface CalculateCategoryConvert {

    CalculateCategoryConvert INSTANCE = Mappers.getMapper(CalculateCategoryConvert.class);

    CalculateCategoryDO convert(CalculateCategoryCreateReqVO bean);

    CalculateCategoryDO convert(CalculateCategoryUpdateReqVO bean);

    CalculateCategoryRespVO convert(CalculateCategoryDO bean);

    List<CalculateCategoryRespVO> convertList(List<CalculateCategoryDO> list);

    PageResult<CalculateCategoryRespVO> convertPage(PageResult<CalculateCategoryDO> page);

    List<CalculateCategoryExcelVO> convertList02(List<CalculateCategoryDO> list);

}
