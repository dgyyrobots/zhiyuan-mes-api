package com.dofast.module.trade.convert.calculate;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateRecordCreateReqVO;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateRecordExcelVO;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateRecordRespVO;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateRecordUpdateReqVO;
import com.dofast.module.trade.dal.dataobject.calculate.CalculateRecordDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 计价记录 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface CalculateRecordConvert {

    CalculateRecordConvert INSTANCE = Mappers.getMapper(CalculateRecordConvert.class);

    CalculateRecordDO convert(CalculateRecordCreateReqVO bean);

    CalculateRecordDO convert(CalculateRecordUpdateReqVO bean);

    CalculateRecordRespVO convert(CalculateRecordDO bean);

    List<CalculateRecordRespVO> convertList(List<CalculateRecordDO> list);

    PageResult<CalculateRecordRespVO> convertPage(PageResult<CalculateRecordDO> page);

    List<CalculateRecordExcelVO> convertList02(List<CalculateRecordDO> list);

}
