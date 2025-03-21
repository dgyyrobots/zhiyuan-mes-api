package com.dofast.module.report.convert.goviewcode;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.report.controller.admin.goview.vo.code.GoViewCodeCreateReqVO;
import com.dofast.module.report.controller.admin.goview.vo.code.GoViewCodeExcelVO;
import com.dofast.module.report.controller.admin.goview.vo.code.GoViewCodeRespVO;
import com.dofast.module.report.controller.admin.goview.vo.code.GoViewCodeUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.report.dal.dataobject.goview.GoViewCodeDO;

/**
 * GoView登录 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface GoViewCodeConvert {

    GoViewCodeConvert INSTANCE = Mappers.getMapper(GoViewCodeConvert.class);

    GoViewCodeDO convert(GoViewCodeCreateReqVO bean);

    GoViewCodeDO convert(GoViewCodeUpdateReqVO bean);

    GoViewCodeRespVO convert(GoViewCodeDO bean);

    GoViewCodeUpdateReqVO convert01(GoViewCodeDO bean);

    List<GoViewCodeRespVO> convertList(List<GoViewCodeDO> list);

    PageResult<GoViewCodeRespVO> convertPage(PageResult<GoViewCodeDO> page);

    List<GoViewCodeExcelVO> convertList02(List<GoViewCodeDO> list);

}
