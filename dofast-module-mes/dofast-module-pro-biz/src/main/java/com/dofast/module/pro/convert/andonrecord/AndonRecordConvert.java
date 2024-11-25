package com.dofast.module.pro.convert.andonrecord;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.pro.controller.admin.andonrecord.vo.*;
import com.dofast.module.pro.dal.dataobject.andonrecord.AndonRecordDO;

/**
 * 安灯呼叫记录 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface AndonRecordConvert {

    AndonRecordConvert INSTANCE = Mappers.getMapper(AndonRecordConvert.class);

    AndonRecordDO convert(AndonRecordCreateReqVO bean);

    AndonRecordDO convert(AndonRecordUpdateReqVO bean);

    AndonRecordRespVO convert(AndonRecordDO bean);

    List<AndonRecordRespVO> convertList(List<AndonRecordDO> list);

    PageResult<AndonRecordRespVO> convertPage(PageResult<AndonRecordDO> page);

    List<AndonRecordExcelVO> convertList02(List<AndonRecordDO> list);

}
