package com.dofast.module.system.convert.form;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.system.controller.admin.form.vo.*;
import com.dofast.module.system.dal.dataobject.form.FormItemDO;

/**
 * 字段 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface FormItemConvert {

    FormItemConvert INSTANCE = Mappers.getMapper(FormItemConvert.class);

    FormItemDO convert(FormItemCreateReqVO bean);

    FormItemDO convert(FormItemUpdateReqVO bean);

    FormItemRespVO convert(FormItemDO bean);

    List<FormItemRespVO> convertList(List<FormItemDO> list);

    PageResult<FormItemRespVO> convertPage(PageResult<FormItemDO> page);

    List<FormItemExcelVO> convertList02(List<FormItemDO> list);

}
