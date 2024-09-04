package com.dofast.module.trade.convert.calculatemodel;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.calculate.calculatemodel.vo.CalculateModelCreateReqVO;
import com.dofast.module.trade.controller.admin.calculate.calculatemodel.vo.CalculateModelExcelVO;
import com.dofast.module.trade.controller.admin.calculate.calculatemodel.vo.CalculateModelRespVO;
import com.dofast.module.trade.controller.admin.calculate.calculatemodel.vo.CalculateModelUpdateReqVO;
import com.dofast.module.trade.dal.dataobject.calculatemodel.CalculateModelDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 计价模型 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface CalculateModelConvert {

    CalculateModelConvert INSTANCE = Mappers.getMapper(CalculateModelConvert.class);

    CalculateModelDO convert(CalculateModelCreateReqVO bean);

    CalculateModelDO convert(CalculateModelUpdateReqVO bean);

    CalculateModelRespVO convert(CalculateModelDO bean);

    List<CalculateModelRespVO> convertList(List<CalculateModelDO> list);

    PageResult<CalculateModelRespVO> convertPage(PageResult<CalculateModelDO> page);

    List<CalculateModelExcelVO> convertList02(List<CalculateModelDO> list);

}
