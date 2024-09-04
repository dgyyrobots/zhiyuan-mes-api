package com.dofast.module.system.convert.form;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.system.controller.admin.form.vo.*;
import com.dofast.module.system.dal.dataobject.form.FormRecordDO;

/**
 * 表单历史 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface FormRecordConvert {

    FormRecordConvert INSTANCE = Mappers.getMapper(FormRecordConvert.class);

    FormRecordDO convert(FormRecordCreateReqVO bean);

    FormRecordDO convert(FormRecordUpdateReqVO bean);

    FormRecordRespVO convert(FormRecordDO bean);

    List<FormRecordRespVO> convertList(List<FormRecordDO> list);

    PageResult<FormRecordRespVO> convertPage(PageResult<FormRecordDO> page);

    List<FormRecordExcelVO> convertList02(List<FormRecordDO> list);

}
