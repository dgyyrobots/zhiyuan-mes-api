package com.dofast.module.channel.convert.remark;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.channel.api.remark.dto.RemarkDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.channel.controller.admin.remark.vo.*;
import com.dofast.module.channel.dal.dataobject.remark.RemarkDO;

/**
 * 订单备注 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface RemarkConvert {

    RemarkConvert INSTANCE = Mappers.getMapper(RemarkConvert.class);

    RemarkDO convert(RemarkCreateReqVO bean);

    RemarkDO convert(RemarkUpdateReqVO bean);

    RemarkRespVO convert(RemarkDO bean);

    RemarkDto convert01(RemarkDO bean);

    List<RemarkRespVO> convertList(List<RemarkDO> list);

    PageResult<RemarkRespVO> convertPage(PageResult<RemarkDO> page);

    List<RemarkExcelVO> convertList02(List<RemarkDO> list);

}
