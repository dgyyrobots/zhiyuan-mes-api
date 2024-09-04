package com.dofast.module.iot.convert.group;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.iot.controller.admin.group.vo.*;
import com.dofast.module.iot.dal.dataobject.group.GroupDO;

/**
 * 设备分组 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface GroupConvert {

    GroupConvert INSTANCE = Mappers.getMapper(GroupConvert.class);

    GroupDO convert(GroupCreateReqVO bean);

    GroupDO convert(GroupUpdateReqVO bean);

    GroupRespVO convert(GroupDO bean);

    List<GroupRespVO> convertList(List<GroupDO> list);

    PageResult<GroupRespVO> convertPage(PageResult<GroupDO> page);

    List<GroupExcelVO> convertList02(List<GroupDO> list);

}
