package com.dofast.module.system.convert.ip;

import com.dofast.framework.ip.core.Area;
import com.dofast.module.system.controller.admin.ip.vo.AreaNodeRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AreaConvert {

    AreaConvert INSTANCE = Mappers.getMapper(AreaConvert.class);

    List<AreaNodeRespVO> convertList(List<Area> list);

    AreaNodeRespVO convert(Area area);

}
