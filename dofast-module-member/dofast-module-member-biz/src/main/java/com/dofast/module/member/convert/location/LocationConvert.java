package com.dofast.module.member.convert.location;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.member.controller.admin.loction.vo.LocationCreateReqVO;
import com.dofast.module.member.controller.admin.loction.vo.LocationExcelVO;
import com.dofast.module.member.controller.admin.loction.vo.LocationRespVO;
import com.dofast.module.member.controller.admin.loction.vo.LocationUpdateReqVO;
import com.dofast.module.member.dal.dataobject.location.LocationDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 三级位置信息 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface LocationConvert {

    LocationConvert INSTANCE = Mappers.getMapper(LocationConvert.class);

    LocationDO convert(LocationCreateReqVO bean);

    LocationDO convert(LocationUpdateReqVO bean);

    LocationRespVO convert(LocationDO bean);

    List<LocationRespVO> convertList(List<LocationDO> list);

    PageResult<LocationRespVO> convertPage(PageResult<LocationDO> page);

    List<LocationExcelVO> convertList02(List<LocationDO> list);

}
